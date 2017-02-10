package stock.po;

/**
 * Created by heiqie on 2017/1/18.
 */
public class StockInfo {
    private int id;
    private String stockId;
    private String stockName;

    @Override
    public String toString() {
        return "StockInfo{" +
                "id=" + id +
                ", stockId='" + stockId + '\'' +
                ", stockName='" + stockName + '\'' +
                '}';
    }
}
