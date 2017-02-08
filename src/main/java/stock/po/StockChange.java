package stock.po;

import java.util.Date;

/**
 * Created by heiqie on 2017/1/18.
 */

public class StockChange {
    private long id;
    private long stockId;
    private String stockName;
    private double latestPrice;
    private double riseFallRate;
    private int active;
    private Date createTime;
}
