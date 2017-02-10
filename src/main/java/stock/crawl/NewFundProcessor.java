package stock.crawl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * Created by heiqie on 2017/2/9.
 */
public class NewFundProcessor implements PageProcessor {

    private final String REG = "http";

    private int count = 1;

    private static final String prefix= "https://e.lufunds.com/jijin/allFund?subType=&haitongGrade=&fundGroupId=&currentPage=";
    private static final String suffix = "&orderType=twelve_month_increase_desc&canFixInvest=&searchWord=#sortTab";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");



    @Override
    public void process(Page page) {
//        System.out.println(page.getHtml());
//        List<String> trs = page.getHtml().xpath("div[@id='licai']//div[@class='list']/a").all();
//        List<String> list = page.getHtml().xpath("div[@class='listTable']//table[@id='fundTable']/tbody/td").all();
        System.out.println("first ------------------");
        List<String> list = page.getHtml().xpath("div[@class='listTable']/table[@id='fundTable']/tbody/tr").all();
//        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {

            Html h = new Html(list.get(i).replace("td","div"));
//            System.out.println(h);
            System.out.println("基金代码:"+h.xpath("//div[1]/text()"));
            System.out.println("基金简介:"+h.xpath("//div[2]/a/text()"));
            System.out.println("最新净值:"+h.xpath("//div[3]/p[1]/text()"));
            System.out.println("时间:"+h.xpath("//p[2]/text()"));
            System.out.println("日增长率:"+h.xpath("//div[4]/span/text()"));
            System.out.println("最近一月增长率:"+h.xpath("//div[5]/span/text()"));
            System.out.println("最近三月增长率:"+h.xpath("//div[6]/span/text()"));
            System.out.println("最近一年增长率:"+h.xpath("//div[7]/span/text()"));
            System.out.println("今年增长率:"+h.xpath("//div[8]/span/text()"));
            System.out.println("成立以来增长率:"+h.xpath("//div[9]/span/text()"));
            System.out.println("起投金额:"+h.xpath("//div[10]/text()"));

//            System.out.println(list.get(i));
            System.out.println("-------");

        }
//        synchronized (this){
//            if (count<=250){
//                page.addTargetRequest(prefix+count+suffix);
//                count++;
//            }
//        }
        System.out.println("size:"+list.size());
        synchronized (this){
            System.out.println("current : "+count);
            count++;
        }


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new NewFundProcessor())
                .addUrl("https://e.lufunds.com/jijin/allFund?fundGroupId=8472#sortTab")
                .thread(5)
                .run();
    }
}
