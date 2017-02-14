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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiqie on 2017/2/8.
 */
public class CompanyProcessor implements PageProcessor {

    private Logger log = LoggerFactory.getLogger(CompanyProcessor.class);

    private ApplicationContext context;

    private CompanyService companyService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public CompanyProcessor() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        companyService = (CompanyService) context.getBean("companyService");
    }

    public void process(Page page) {


        String body = page.getHtml().xpath("//body/text()").get();
        body = body.replace("(", "").replace("[", "").replace("]", "").replace(")", "");
        String[] temp = body.split("\",");
        List<Company> companies = new ArrayList<Company>();
        if (temp != null && temp.length > 0) {
            temp[0] = temp[0].replace("\"", "");
            for (int i = 1; i < temp.length; i++) {
                temp[i] = temp[i].replace("\"", "");
            }
            for (String t : temp) {
                String[] elements = t.split(",");
                if (elements != null && elements.length > 0) {
                    Company company = new Company();
                    company.setCompanyCode(elements[1]);
                    company.setCompanyName(elements[2]);
                    companies.add(company);
                }
            }
        }
        System.out.println("insert batch company over:");
        int result = companyService.insertAll(companies);
        System.out.println(result);


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
        log.info("ee");

        Company company = new Company();
        company.setCompanyName("test");
        int result = companyService.insert(company);
        System.out.println(result);
    }
}
