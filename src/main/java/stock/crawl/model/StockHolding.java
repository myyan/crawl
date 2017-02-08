package stock.crawl.model;

import java.util.Date;

/**
 * Created by heiqie on 2017/2/8.
 */
public class StockHolding {
    private long id;
    private String fundCode;
    private String fundName;
    private String stockCode;
    private long holdNum;
    private double proportionFund;
    private long holdChange;
    private double marketValue;
    private double proportionNet;
    private double proportionStock;

    private Date created;
    private String createdby;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public long getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(long holdNum) {
        this.holdNum = holdNum;
    }

    public double getProportionFund() {
        return proportionFund;
    }

    public void setProportionFund(double proportionFund) {
        this.proportionFund = proportionFund;
    }

    public long getHoldChange() {
        return holdChange;
    }

    public void setHoldChange(long holdChange) {
        this.holdChange = holdChange;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getProportionNet() {
        return proportionNet;
    }

    public void setProportionNet(double proportionNet) {
        this.proportionNet = proportionNet;
    }

    public double getProportionStock() {
        return proportionStock;
    }

    public void setProportionStock(double proportionStock) {
        this.proportionStock = proportionStock;
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
        return "StockHolding{" +
                "id=" + id +
                ", fundCode='" + fundCode + '\'' +
                ", fundName='" + fundName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", holdNum=" + holdNum +
                ", proportionFund=" + proportionFund +
                ", holdChange=" + holdChange +
                ", marketValue=" + marketValue +
                ", proportionNet=" + proportionNet +
                ", proportion_stock=" + proportionStock +
                ", created=" + created +
                ", createdby='" + createdby + '\'' +
                '}';
    }
}
