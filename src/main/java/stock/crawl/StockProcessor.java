package stock.crawl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import stock.crawl.model.Stock;
import stock.crawl.model.StockHolding;
import stock.crawl.service.StockService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StockProcessor implements PageProcessor {

    private StockService stockService = new StockService();

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    private final String LIST_URL="http://fund\\.jrj\\.com\\.cn/action/fhs/list\\.jspa";
    private final String HOLDING_URL="http://fund\\.jrj\\.com\\.cn/fhs/detail/\\d{8}/\\d{6}\\.shtml";



    public List<String> urlList=new ArrayList<String>();


    @Override
    public void process(Page page) {

        //基金重创股列表页
        if(page.getUrl().regex(LIST_URL).match()){
            WebDriver driver=new ChromeDriver();
            driver.get("http://fund.jrj.com.cn/action/fhs/list.jspa");
            WebElement webElement=driver.findElement(By.id("table_zcglb"));
            String str=webElement.getAttribute("outerHTML");
            System.out.println(str);
            driver.close();
            Html html=new Html(str);
            List<String> list=html.xpath("//tbody/tr").all();


            for(int i=0;i<list.size();i++){
                String s=list.get(i);
                s=s.replace("tr","div").replace("td","div");
                Html h=new Html(s);





                String url=h.xpath("//div[@class='tl w3']").links().get();
                url="http://fund.jrj.com.cn"+url;
                urlList.add(url);
//                page.addTargetRequest(url);

                long id= Long.parseLong(h.xpath("//div[@class='tc w1']/text()").get());
                String stockCode=h.xpath("//div[@class='tc w2']/a/text()").get();
                String stockName=h.xpath("//div[@class='tl w3']/a/text()").get();

                //http://fund.jrj.com.cn/fhs/detail/20160930/600519.shtml



                long fundNum= getLongValue(h.xpath("//div[@class='w4']/text()").get());
                long stockSum=getLongValue(h.xpath("//div[@class='w5']/text()").get());

                long stockChange= getLongValue(h.xpath("//div[@class='w6']/text()").get());

                double marketValue=getDoubleValue(h.xpath("//div[@class='w7']/text()").get());

                double proportion=getDoubleValue(h.xpath("//div[@class='w8']/text()").get());

                double proportionChange=getDoubleValue(h.xpath("//div[@class='w9']/text()").get());


                Stock stock=new Stock();
                stock.setId(id);
                stock.setStockCode(stockCode);
                stock.setStockName(stockName);
                stock.setFundNum(fundNum);
                stock.setStockSum(stockSum);
                stock.setStockChange(stockChange);
                stock.setMarketValue(marketValue);
                stock.setProportion(proportion);
                stock.setProportionChange(proportionChange);
                stock.setCreatedby("lingxiao");

//                stockService.insertStock(stock);


            }

        }else if(page.getUrl().regex(HOLDING_URL).match()){
            //基金持股页
            String url=page.getUrl().get();
            int index=url.indexOf(".shtml");
            String stockCode=url.substring(index-6,index);

            List<String> list=page.getHtml().xpath("//div[@class='data']/div[@class='in']/div[@class='dmain']/table/tbody/tr").all();
            for(int i=1;i<list.size();i++){
                String s=list.get(i);
                s=s.replace("tr","div").replace("td","div");
                Html h=new Html(s);
//                System.out.println(h);
                String fundCode=h.xpath("//div[@class='tc']/text()").get();
                String fundName=h.xpath("//div[@class='tl']/a/text()").get();
//                System.out.println(h.xpath("//div[@class='tl']/a"));
                List<String> contents=h.xpath("//body/div/div").all();
                long holdNum= getLongValue(getText(contents.get(2)));
                double proportionFund= getDoubleValue(getText(contents.get(3)));
                long holdChange= getLongValue(getText(contents.get(4)));

                double marketValue=getDoubleValue(getText(contents.get(5)));
                double proportionNet= getDoubleValue(getText(contents.get(6)));
                double proportionStock= getDoubleValue(getText(contents.get(7)));

                StockHolding stockHolding=new StockHolding();
                stockHolding.setFundCode(fundCode);
                stockHolding.setFundName(fundName);
                stockHolding.setStockCode(stockCode);
                stockHolding.setHoldNum(holdNum);
                stockHolding.setProportionFund(proportionFund);
                stockHolding.setHoldChange(holdChange);
                stockHolding.setMarketValue(marketValue);
                stockHolding.setProportionNet(proportionNet);
                stockHolding.setProportionStock(proportionStock);
                stockHolding.setCreatedby("lingxiao");

//                System.out.println(stockHolding);
                stockService.insertStockHolding(stockHolding);

            }
        }

    }

    private double getDoubleValue(String text) {
        if(text.contains(",")){
            text=text.replace(",","");
        }
        if(text!=null&& !text.equals("")&&!text.equals("null")&&!text.equals("--")){
            return Double.parseDouble(text);
        }
        return 0;
    }

    private long getLongValue(String text) {
        if(text.contains(",")){
            text=text.replace(",","");
        }
        if(text!=null&& !text.equals("")&&!text.equals("null")&&!text.equals("--")){
            return Long.parseLong(text);
        }
        return 0;
    }

    private String getText(String html){
        Pattern p=Pattern.compile("<[^>]+>");
        Matcher m=p.matcher(html);
        html=m.replaceAll("");
        return html.replace("\n","").replace(" ","");
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        StockProcessor stockProcessor=new StockProcessor();
        Spider.create(stockProcessor)
                .addUrl("http://fund.jrj.com.cn/action/fhs/list.jspa")
                //开启5个线程抓取
                .thread(5)
                .run();
    }



}