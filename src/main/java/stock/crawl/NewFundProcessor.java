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
        fundService = (FundService) context.getBean("fundService");
    }

    private FundService fundService;

    private static final String prefix = "https://e.lufunds.com/jijin/allFund?subType=&haitongGrade=&fundGroupId=&currentPage=";
    private static final String suffix = "&orderType=twelve_month_increase_desc&canFixInvest=&searchWord=#sortTab";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    @Override
    public void process(Page page) {
        List<String> list = page.getHtml().xpath("div[@class='listTable']/table[@id='fundTable']/tbody/tr").all();

        for (int i = 0; i < list.size(); i++) {
            System.out.println("size:" + list.size());
            Html h = new Html(list.get(i).replace("td", "div"));
            String fundCode = h.xpath("//div[1]/text()").get();
            String fundName = h.xpath("//div[2]/a/text()").get();
            String oneDayGrowthRate = h.xpath("//div[4]/span/text()").get();
            String oneMonthGrowthRate = h.xpath("//div[5]/span/text()").get();
            String latestNetWorth = h.xpath("//div[3]/p[1]/text()").get();
            Double netWorth = stringToDouble(latestNetWorth);
            String threeMonthGrowthRate = h.xpath("//div[6]/span/text()").get();
            String oneYearGrowthRate = h.xpath("//div[7]/span/text()").get();
            String thisYearGrowthRate = h.xpath("//div[8]/span/text()").get();
            String sinceFoundedGrowthRate = h.xpath("//div[9]/span/text()").get();
            String sAmount = h.xpath("//div[10]/text()").get();
            Float startAmount = stringToFloat(sAmount);

            Fund fund = new Fund();
            fund.setFundCode(fundCode);
            fund.setFundName(fundName);
            fund.setLatestNetWorth(netWorth);
            fund.setOneDayGrowthRate(growthRateFormatter(oneDayGrowthRate));
            fund.setOneMonthGrowthRate(growthRateFormatter(oneMonthGrowthRate));
            fund.setThreeMonthGrowthRate(growthRateFormatter(threeMonthGrowthRate));
            fund.setOneYearGrowthRate(growthRateFormatter(oneYearGrowthRate));
            fund.setSinceThisYearGrowthRate(growthRateFormatter(thisYearGrowthRate));
            fund.setSinceFoundedGrowthRate(growthRateFormatter(sinceFoundedGrowthRate));
            fund.setStartAmount(startAmount);







        }
    }

    private static Float stringToFloat(String str) {
        if (str == null) {
            return null;
        } else if (str.contains(",")) {
            str = str.replace(",", "");
            return Float.valueOf(str);
        } else {
            return Float.valueOf(str);
        }
    }

    private static Double stringToDouble(String str) {
        if (str == null) {
            return null;
        } else {
            return Double.valueOf(str);
        }
    }

    private static Float growthRateFormatter(String growthRate) {
        if (growthRate == null) {
            return null;
        } else {
            growthRate = growthRate.replace("%", "");
            return Float.valueOf(growthRate);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        List<String> urls = new ArrayList<String>();
        for (int i = 1; i <= 250; i++) {
            String url = prefix + i + suffix;
            urls.add(url);
        }

        NewFundProcessor processor = new NewFundProcessor();
        Spider.create(processor)
                .startUrls(urls)
                .thread(10)
                .runAsync();
    }
}
