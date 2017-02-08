package stock.crawl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.service.StockInfoService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by heiqie on 2017/1/18.
 */

public class MyHomePageProcessor implements PageProcessor {
    Logger log = LoggerFactory.getLogger(MyHomePageProcessor.class);
    private StockInfoService stockInfoService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(5000).setTimeOut(6000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    private final String LIST_URL = "http://www\\.jianshu\\.com/u/12ef8ca09f49";

    private final String STORE_URL = ".*/p/.*";


    private List<String> stringList = new ArrayList<String>();

    @Override
    public void process(Page page) {
        System.out.println("entry process");
        log.debug("entry process");
//        System.getProperties().setProperty("webdriver.chrome.driver","C:/Windows/System32/chromedriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.jianshu.com/u/12ef8ca09f49");
        String title = driver.getTitle();
        System.out.println(title);
        WebElement webElement = driver.findElement(By.className("table search_"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);
        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MyHomePageProcessor())
                .addUrl("http://www.jianshu.com/u/12ef8ca09f49")
                .thread(4)
                .run();
    }
}




