package stock.crawl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.crawl.model.JsonData;
import stock.crawl.model.TestData;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.io.IOException;

/**
 * Created by heiqie on 2017/1/20.
 */
public class EastMoneyPageProcessor implements PageProcessor {

    private final static Logger log = LoggerFactory.getLogger(EastMoneyPageProcessor.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    private static final String str = "http://data\\.eastmoney\\.com/zlsj/zlsj_list\\.aspx\\?type=ajax&st=2&sr=-1&p=6&ps=50&jsObj=DOvPxesJ&stat=1&cmd=1&date=2016-09-30&rt=49497063";

    public void process(Page page) {

        String rawText = page.getRawText().toString();
        String[] texts = rawText.split("=");
        String text = texts[1];
        System.out.println(text);
        Json json = new Json(text);
        System.out.println(json);
        String jsonString = "{\"pages\":123,\"name\":\"haozi\",\"info\":[1,2,3]}";
        Json testJson = new Json(jsonString);
        System.out.println(testJson);
        text = text.replace("pages","\"pages\"").replace("data","\"data\"");
        System.out.println(text);
        ObjectMapper mapper = new ObjectMapper();
        try {
            TestData data = mapper.readValue(jsonString, TestData.class);
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String data = json.jsonPath("$.data").get();
//        System.out.println(data);


    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new EastMoneyPageProcessor())
                .addUrl("http://data.eastmoney.com/zlsj/zlsj_list.aspx?type=ajax&st=2&sr=-1&p=6&ps=50&jsObj=DOvPxesJ&stat=1&cmd=1&date=2016-09-30&rt=49497063")
                .run();
    }
}
