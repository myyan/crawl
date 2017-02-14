package stock.crawl;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by heiqie on 2017/2/10.
 */
public class SJSCompanyProcessor implements PageProcessor{

    public void process(Page page) {
        System.out.println(page.getHtml());
    }

    public Site getSite() {
        return null;
    }

    public static void main(String[] args) {
    }
}
