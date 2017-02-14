package stock.crawl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.junit.Test;
import stock.domain.DataJson;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by heiqie on 2017/2/13.
 */
public class TestProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");


    public void process(Page page) {
        System.out.println(page.getHtml());
//        System.out.println(page.getHtml().xpath("//body/text()"));
//        String jsonString = page.getHtml().xpath("//body/text()").get();
//        System.out.println(jsonString);
//        String tempString = jsonString.split("=")[1].replace("'","\"");
//              ;
//        System.out.println(tempString);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        try {
//            DataJson data = mapper.readValue(tempString, DataJson.class);
//            System.out.println(data.getData1().size());
//            System.out.println(data.getData1().get(0));
//            String []info = data.getData1().get(0).split(",");
//            System.out.println(info.length);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestProcessor())
//                .addUrl("http://stock.gtimg.cn/data/index.php?appn=rank&t=ranka/chr&p=3&o=0&l=10&v=list_data")
//                .addUrl("http://stock.gtimg.cn/data/index.php?appn=rank&t=ranka/chr&p=3&o=0&l=2000&v=list_data")
//                .addUrl("http://stock.gtimg.cn/data/index.php?appn=rank&t=ranka/chr&p=3&o=0&l=1000&v=list_data")
//                .addUrl("http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=zzf&st=desc&sd=2016-02-13&ed=2017-02-13&qdii=&tabSubtype=,,,,,&pi=55&pn=50&dx=1&v=0.7633091300316857")
                .addUrl("http://fund.eastmoney.com/data/rankhandler.aspx?op=ph&dt=kf&ft=all&rs=&gs=0&sc=zzf&st=desc&sd=2016-02-13&ed=2017-02-13&qdii=&tabSubtype=,,,,,&pi=55&pn=50&dx=1&v=0.7633091300316857")
                .thread(4)

                .run();
    }



}
