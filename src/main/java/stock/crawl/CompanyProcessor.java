package stock.crawl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by heiqie on 2017/2/8.
 */
public class CompanyProcessor implements PageProcessor {

    private Logger log = LoggerFactory.getLogger(CompanyProcessor.class);

    private ApplicationContext context;

    private CompanyService companyService;

    private boolean isFirst;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public CompanyProcessor() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        companyService = (CompanyService) context.getBean("companyService");
    }

    public void process(Page page) {
        List<Company> insertCompanys = new ArrayList<>();
        List<Company> updateCompanys = new ArrayList<>();
        List<Company> companyList = companyService.selectAll();
        List<String> companyCodes = companyList.stream().map(Company::getCompanyCode).collect(Collectors.toList());
        String body = page.getHtml().xpath("//body/text()").get();
        body = body.replace("(", "").replace("[", "").replace("]", "").replace(")", "");
        String[] temp = body.split("\",");
        if (temp != null && temp.length > 0) {
            temp[0] = temp[0].replace("\"", "");
            for (int i = 1; i < temp.length; i++) {
                temp[i] = temp[i].replace("\"", "");
            }
            for (String t : temp) {
                String[] elements = t.split(",");
                if (elements != null && elements.length > 0) {


                    String companyCode = elements[1];
                    String stockName = elements[2];
                    if (companyCodes.contains(companyCode)) {
                        Company company = companyList.stream().filter(x -> x.getCompanyCode().equals(companyCode)).findFirst().get();
                        company.setStockName(stockName);
                        updateCompanys.add(company);
                    } else {
                        Company company = new Company();
                        company.setCompanyCode(companyCode);
                        company.setStockName(stockName);
                        company.setActive(1);
                        if (companyCode != null) {
                            int code = Integer.valueOf(companyCode);
                            if (code > 600000) {
                                company.setType(0);
                            } else {
                                company.setType(1);
                            }
                        }
                        insertCompanys.add(company);
                    }

                }
            }
        }
        log.info("before batch insert operation:");
        try {
            if (updateCompanys.size() != 0) {
//                int result2 = companyService.batchUpdate(updateCompanys);
                updateCompanys.forEach(x->{
                    int result = companyService.update(x);
                    log.info("result:{}", result);
                });

            }
            if (insertCompanys.size() != 0) {
                int result = companyService.insertAll(insertCompanys);
                log.info("result :{}", result);
            }

        } catch (Exception e) {
            log.info("exception occur:{}", e);
            if (e instanceof MySQLIntegrityConstraintViolationException) {
                log.info("ok");
            }
        }


    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new CompanyProcessor())
                .addUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page=1&pageSize=5000&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6604759531608846")
                .thread(5)
                .run();
    }

    @Test
    public void test() {
        log.info("start");

        Company company = new Company();
        company.setCompanyName("test");
        company.setType(1);





        int result = companyService.insert(company);
        System.out.println(result);
    }

    @Test
    public void test2() {
        List<Company> companies = companyService.selectByType(0);
        for (Company c : companies) {
            System.out.println(c);
        }
    }

    @Test
    public void testUpdate() {
        Company company = companyService.selectByCode("600000");
        log.info("company:{}", company);
        company.setCompanyName("测试1");
        List<Company> companies = Arrays.asList(company);
//        int result = companyService.update(company);
        int result = companyService.batchUpdate(companies);
        log.info("result :{}", result);
    }


}
