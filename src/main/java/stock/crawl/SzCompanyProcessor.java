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
        System.out.println(page.getRawText());
        System.out.println("url:"+page.getUrl());



    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws IOException {
//        HttpClient client = new DefaultHttpClient();
//        HttpGet get = new HttpGet("https://www.baidu.com");
//        HttpGet httpGet = new HttpGet("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6434791462295828");
//        HttpResponse response = client.execute(get);
        URL url = new URL("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6434791462295828");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        OutputStream stream = connection.getOutputStream();
        System.out.println(stream);
//        System.out.println(response);
//        System.out.println(response.getEntity());
//        Spider.create(new SzCompanyProcessor())
////                .addUrl("https://www.baidu.com")
//                .addUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._A&sty=FCOIATA&sortType=C&sortRule=-1&page=2&pageSize=20&js=var%20quote_123%3d{rank:[(x)],pages:(pc)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.6434791462295828")
//                .thread(5)
//                .run();
    }
}
