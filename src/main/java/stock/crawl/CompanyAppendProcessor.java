package stock.crawl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.po.Company;
import stock.service.CompanyService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by heiqie on 2017/2/15.
 */
public class CompanyAppendProcessor implements PageProcessor {

    private Logger log = LoggerFactory.getLogger(CompanyProcessor.class);

    private ApplicationContext context;

    private CompanyService companyService;

    private static final String industryPrefix = "http://quote.eastmoney.com/";
    private static final String industrySuffix = ".html";
    private static final String companyPrefix = "http://f9.eastmoney.com/";
    private static final String companySuffix = ".html";

    private static final String INDUSTRY_URL = "http://quote\\.eastmoney\\.com/\\w+\\.html";
    private static final String COMPANY_URL = "http://f9\\.eastmoney\\.com/\\w+\\.html";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public CompanyAppendProcessor() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        companyService = (CompanyService) context.getBean("companyService");
    }

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(INDUSTRY_URL).match()) {
            log.info("industry url:{}", page.getUrl());
            String companyCode = getCompanyCodeByUrl(page.getUrl().get());
            log.info("companyCode:{}", companyCode);
            log.info(page.getUrl().get());
            String industry = page.getHtml().xpath("/html/body/div[13]/div[2]/div[2]/div[2]/div[4]/table/tbody/tr[2]/td[1]/a/text()").get();
            log.info("industry:{}", industry);
            Company company = companyService.selectByCode(companyCode);
            if (industry.equals("-")) {
                industry = null;
            }
            company.setIndustry(industry);
            companyService.update(company);
            log.info("company:{}", company);
        } else if (page.getUrl().regex(COMPANY_URL).match()) {

            List<String> strings = page.getHtml().xpath("//div[@class='box_lr']/div[@class='box_l']").all();
            String info1 = strings.get(strings.size() - 1);
            Html html = new Html(info1);
            List<String> tds = html.xpath("//td//text()").all();
            String companyName = tds.get(1);
            String legalPerson = tds.get(3);
            String registerAddress = tds.get(5);
            String registerCapital = tds.get(7);
            LocalDate establishDate = LocalDate.parse(tds.get(9));
            LocalDate listingDate = LocalDate.parse(tds.get(11));
            String companyCode = getCompanyCodeByUrl(page.getUrl().get());
            Company company = companyService.selectByCode(companyCode);
            if (company != null) {
                company.setCompanyName(companyName);
                company.setLegalRepresentative(legalPerson);
                company.setRegisterAddress(registerAddress);
                company.setRegisterCapital(registerCapital);
                company.setEstablishDate(localDateToDate(establishDate));
                company.setListingDate(localDateToDate(listingDate));
                int result = companyService.update(company);
                log.info("result:{}", result);
            }

            System.out.println(companyName);
            System.out.println(legalPerson);

            System.out.println(registerAddress);
            System.out.println(registerCapital);
            System.out.println(establishDate);
            System.out.println(listingDate);

            log.info("company url:{}", page.getUrl());
        }

    }

    private static String getCompanyCodeByUrl(String url) {
        String regEx = "\\d{6}";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(url);
        matcher.find();
        String companyCode = matcher.group(0);
        return companyCode;
    }

    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

    }
    @Test
    public void test() {
        List<String> companyCodes = companyService.selectAll()
                .stream().map(Company::getCompanyCode).collect(Collectors.toList());
        List<String> urls = new ArrayList<>();
        List<String> industryUrls = companyCodes.stream().map(item -> {
            int code = Integer.valueOf(item);
            if (code >= 600000) {
                //TODO MarketEnum instead
                urls.add(companyPrefix + "sh" + item + companySuffix);
                return industryPrefix + "sh" + item + industrySuffix;
            } else {
                urls.add(companyPrefix + "sz" + item + companySuffix);
                return industryPrefix + "sz" + item + industrySuffix;
            }
        }).collect(Collectors.toList());
        urls.addAll(industryUrls);

        Spider.create(new CompanyAppendProcessor())
                .startUrls(urls)
                .thread(10)
                .run();
    }


    @Test
    public void test2() {
        LocalDate date = LocalDate.parse("1992-10-19");
        System.out.println(date);
        Company company = companyService.selectOne(1);
        company.setEstablishDate(localDateToDate(date));
        companyService.update(company);
    }
}
