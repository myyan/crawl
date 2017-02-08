package stock.crawl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by heiqie on 2017/1/19.
 */
public class SpiderTest implements PageProcessor {
    public void process(Page page) {
        System.out.println("url : " + page.getUrl().toString());
        System.out.println(page.getHtml());
        page.putField("content", page.getHtml());
    }

    public Site getSite() {
        Site me = Site.me();
        me.addStartUrl("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=0&rsv_idx=1&tn=baidu&wd=java&rsv_pq=980d1de300074ddc&rsv_t=c3d5k%2FvumWjIXUkqcp2e0s%2FeHmvS6U3%2FPgtA9Ssorc05%2BX7y0iDu9w0Qvy0&rqlang=cn&rsv_enter=1&rsv_sug3=4&rsv_sug1=1&rsv_sug7=100&rsv_sug2=0&inputT=872&rsv_sug4=873&rsv_sug=2");
        return me.setDomain("www.baidu.com")
                .setTimeOut(1000)
                .setRetryTimes(5)
                .setSleepTime(1000);
    }

    public static void main(String[] args) {
        String chrome_driver_path = "C:\\Windows\\System32\\chromedriver.exe";
        String base_dir = "D:\\";
        Spider spider = Spider.create(new SpiderTest());
        spider.addPipeline(new FilePipeline(base_dir))
                .setDownloader(new SeleniumDownloader(chrome_driver_path).setSleepTime(5000))
                .thread(1)
                .start();
        while (spider.getStatus() != Spider.Status.Stopped) ;
        System.out.println("Spider end!");

    }
}
