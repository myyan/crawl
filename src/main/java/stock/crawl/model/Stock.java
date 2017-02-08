package stock.crawl.model;

import java.util.Date;

/**
 * Created by heiqie on 2017/2/8.
 */
public class Stock {
    private long id;
    private String stockCode;
    private String stockName;
    private long fundNum;
    private long stockSum;
    private long stockChange;
    private double marketValue;
    private double proportion;
    private double proportionChange;
    private Date created;
    private String createdby;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public long getFundNum() {
        return fundNum;
    }

    public void setFundNum(long fundNum) {
        this.fundNum = fundNum;
    }

    public long getStockSum() {
        return stockSum;
    }

    public void setStockSum(long stockSum) {
        this.stockSum = stockSum;
    }

    public long getStockChange() {
        return stockChange;
    }

    public void setStockChange(long stockChange) {
        this.stockChange = stockChange;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    public double getProportionChange() {
        return proportionChange;
    }

    public void setProportionChange(double proportionChange) {
        this.proportionChange = proportionChange;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", fundNum=" + fundNum +
                ", stockSum=" + stockSum +
                ", stockChange=" + stockChange +
                ", marketValue=" + marketValue +
                ", proportion=" + proportion +
                ", proportionChange=" + proportionChange +
                ", created=" + created +
                ", createdby='" + createdby + '\'' +
                '}';
    }

}
