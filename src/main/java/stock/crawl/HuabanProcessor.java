package stock.crawl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

/**
 * Created by heiqie on 2017/1/19.
 */
public class HuabanProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(10000).setTimeOut(30000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    @Override
    public void process(Page page) {
        String info = page.getHtml().toString();
        System.out.println(info);

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000");
        String title = driver.getTitle();
        System.out.println("title:"+title);
        System.out.println("come here");
        Html html = page.getHtml();
        String pageInfo = html.toString();
        System.out.println(pageInfo);
        page.addTargetRequests(page.getHtml().links().regex("http://huaban\\.com/.*").all());
        if (page.getUrl().toString().contains("pins")) {

            page.putField("img", page.getHtml().xpath("//div[@id='pin_img']/a/img/@src").toString());
        } else {
            page.getResultItems().setSkip(true);
        }
    }

    @Override
    public Site getSite() {
       return site;
    }

    public static void main(String[] args) {
        System.out.println("start");
        Spider.create(new HuabanProcessor())
                .setDownloader(new SeleniumDownloader("C:/Windows/System32/chromedriver.exe").setSleepTime(10000))
                .addUrl("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000")
                .thread(5)
                .run();
    }
}
