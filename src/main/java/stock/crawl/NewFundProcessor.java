package stock.crawl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.po.Fund;
import stock.service.FundService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiqie on 2017/2/9.
 */
public class NewFundProcessor implements PageProcessor {

    private Logger log = LoggerFactory.getLogger(NewFundProcessor.class);

    private ApplicationContext context;

    public NewFundProcessor() {
        context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    private FundService fundService;

    private static final String prefix = "https://e.lufunds.com/jijin/allFund?subType=&haitongGrade=&fundGroupId=&currentPage=";
    private static final String suffix = "&orderType=twelve_month_increase_desc&canFixInvest=&searchWord=#sortTab";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    @Override
    public void process(Page page) {
        System.out.println("first ------------------");
        List<String> list = page.getHtml().xpath("div[@class='listTable']/table[@id='fundTable']/tbody/tr").all();

        for (int i = 0; i < list.size(); i++) {

            Html h = new Html(list.get(i).replace("td", "div"));
            String fundCode = h.xpath("//div[1]/text()").get();
            String fundName = h.xpath("//div[2]/a/text()").get();
            String dailyGrowthRate = h.xpath("//div[4]/span/text()").get();
            String monthGrowthRate = h.xpath("//div[5]/span/text()").get();
            String startAmount = h.xpath("//div[10]/text()").get();
            System.out.println("基金代码:" + h.xpath("//div[1]/text()"));
            System.out.println("基金简介:" + h.xpath("//div[2]/a/text()"));
            System.out.println("最新净值:" + h.xpath("//div[3]/p[1]/text()"));
            System.out.println("时间:" + h.xpath("//p[2]/text()"));
            System.out.println("日增长率:" + h.xpath("//div[4]/span/text()"));
            System.out.println("最近一月增长率:" + h.xpath("//div[5]/span/text()"));
            System.out.println("最近三月增长率:" + h.xpath("//div[6]/span/text()"));
            System.out.println("最近一年增长率:" + h.xpath("//div[7]/span/text()"));
            System.out.println("今年增长率:" + h.xpath("//div[8]/span/text()"));
            System.out.println("成立以来增长率:" + h.xpath("//div[9]/span/text()"));
            System.out.println("起投金额:" + h.xpath("//div[10]/text()"));
            fundService = (FundService) context.getBean("fundService");
            Fund fund = new Fund();
            fund.setFundCode(fundCode);
            fund.setFundName(fundName);
            fund.setDailyGrowthRate(dailyGrowthRate);
            fund.setMonthlyGrowthRate(monthGrowthRate);
            int result = fundService.insert(fund);
            System.out.println(result);

            System.out.println("-------");

        }

        System.out.println("size:" + list.size());


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        List<String> urls = new ArrayList<String>();
        for (int i = 1; i <= 250; i++) {
            String url = prefix+i+suffix;
            urls.add(url);
        }

        NewFundProcessor processor = new NewFundProcessor();
            Spider.create(processor)
                    .startUrls(urls)
                    .thread(10)
                    .runAsync();
//        }

    }
}
