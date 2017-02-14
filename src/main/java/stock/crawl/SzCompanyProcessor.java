package stock.crawl;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by heiqie on 2017/2/10.
 */
public class SzCompanyProcessor implements PageProcessor {

    private final Logger log = LoggerFactory.getLogger(SzCompanyProcessor.class);

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    public void process(Page page) {
        System.out.println(page.getHtml());
        System.out.println(page.getHtml().xpath("table[id='REPORTID_tab1']"));
        System.out.println(page.getRawText());
        System.out.println("url:"+page.getUrl());



    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {

        Spider.create(new SzCompanyProcessor())
                .addUrl("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=601118")
                .thread(5)
                .run();
    }
}
