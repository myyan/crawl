package stock.crawl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stock.crawl.model.FundData;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.io.IOException;

/**
 * Created by heiqie on 2017/2/8.
 */
public class EastMoneyFundPageProcessor implements PageProcessor {

    private final static Logger log = LoggerFactory.getLogger(EastMoneyPageProcessor.class);
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    public void process(Page page) {
        String content = page.getRawText().toString();
//        System.out.println(content);
        String jsonString = content.split("=")[1];
//        System.out.println(jsonString);
        jsonString = jsonString.replace("chars","\"chars\"")
                .replace("datas","\"datas\"")
                .replace("count","\"count\"").
                        replace("record","\"record\"").
                        replace("pages","\"pages\"")
                .replace("curpage","\"curpage\"").
                        replace("indexsy","\"indexsy\"").
                        replace("showday","\"showday\"");
        System.out.println(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        try {
            FundData data=mapper.readValue(jsonString, FundData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new EastMoneyFundPageProcessor())
                .addUrl("http://fund.eastmoney.com/Data/Fund_JJJZ_Data.aspx?t=1&lx=1&letter=&gsid=&text=&sort=zdf,desc&page=1,1&dt=1486531403178&atfc=&onlySale=0")
                .run();
    }
}
