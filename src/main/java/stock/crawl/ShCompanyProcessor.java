package stock.crawl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Arrays;
import java.util.List;

/**
 * Created by heiqie on 2017/2/10.
 */
public class ShCompanyProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    private final String REG = "http://www\\.sse\\.com\\.cn/assortment/stock/areatrade/trade/detail\\.shtml\\?csrcCode=\\w+";

    private final String ORIGIN_URL = "http://www\\.sse\\.com\\.cn/assortment/stock/areatrade/trade/detail\\.shtml\\?csrcCode=C";
    public void process(Page page) {
        if (page.getUrl().regex(ORIGIN_URL).match()){
            WebDriver driver = new ChromeDriver();
            driver.get("http://www.sse.com.cn/assortment/stock/areatrade/trade/detail.shtml?csrcCode=C");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WebElement element = driver.findElement(By.xpath("//table[@class='table search_']//a"));
            String str=element.getAttribute("outerHTML");
            System.out.println(str);
            driver.close();
//            System.out.println(page.getUrl());

//            System.out.println("url:" + page.getUrl());
//            System.out.println(page.getHtml());
//            System.out.println("a://");
//            System.out.println(page.getHtml().xpath("//a").all());
//            System.out.println("list://");
//            List<String> list = page.getHtml().links().all();
//            System.out.println(list);
//            page.addTargetRequests(page.getHtml().links().regex(REG).all());
        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S");
//        for (int i = 0; i < list.size(); i++) {
//            Spider.create(new ShCompanyProcessor())
//                    .addUrl("http://www.sse.com.cn/assortment/stock/areatrade/trade/detail.shtml?csrcCode="+list.get(i))
//                    .thread(5)
//                    .runAsync();
//        }
        Spider.create(new ShCompanyProcessor())
                .addUrl("http://www.sse.com.cn/assortment/stock/areatrade/trade/detail.shtml?csrcCode=C")
                .thread(5)
                .run();
    }
}
