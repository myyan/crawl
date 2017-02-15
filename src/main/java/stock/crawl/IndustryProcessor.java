package stock.crawl;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.po.Company;
import stock.po.Industry;
import stock.service.CompanyService;
import stock.service.IndustryService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiqie on 2017/2/14.
 */
public class IndustryProcessor implements PageProcessor {

    private Logger log = LoggerFactory.getLogger(CompanyProcessor.class);


    private static final String prefix = "http://quote.eastmoney.com/";
    private static final String suffix = ".html";

    private ApplicationContext context;

    private CompanyService companyService;
    private IndustryService industryService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public IndustryProcessor() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        companyService = (CompanyService) context.getBean("companyService");
        industryService = (IndustryService) context.getBean("industryService");
    }


    public void process(Page page) {

        List<String> list = page.getHtml().xpath("/html/body/div[1]/div[6]/div[4]//li").all();
        List<Industry> industries = new ArrayList<>();
        for (int i = 0; i < list.size(); i=i+2) {
            Industry industry = new Industry();
            Html h1 = new Html(list.get(i));
            Html h2 = new Html(list.get(i + 1));
            String industryCode = h1.xpath("//b/text()").get();
            String industryName = h2.xpath("//a/text()").get();
            industry.setIndustryCode(industryCode);
            industry.setIndustryName(industryName);
            industries.add(industry);
        }
        int result = industryService.insertAll(industries);
        log.info("result:{}",result);

    }

    public Site getSite() {
        return site;
    }

    @Test
    public void test() {
        List<Company> companyList = companyService.selectByType(0);
        List<String> urls = new ArrayList<>();
        if (companyList != null && companyList.size() != 0) {
            for (Company c : companyList) {
                urls.add(prefix + "sh" + c.getCompanyCode() + suffix);
            }
        }

        Spider.create(new IndustryProcessor())
                .startUrls(urls)
                .thread(10)
                .run();
    }

    @Test
    public void test2() {
        Spider.create(new IndustryProcessor())
                .addUrl("http://stock.eastmoney.com/hangye.html")
                .thread(5)
                .run();
    }
}
