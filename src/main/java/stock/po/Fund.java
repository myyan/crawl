package stock.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Created by heiqie on 2017/2/9.
 */
@Data
public class Fund {
    private int id;
    private String fundCode;
    private String fundName;
    private Double latestNetWorth;
    private Float oneDayGrowthRate;
    private Float oneMonthGrowthRate;
    private Float threeMonthGrowthRate;
    private Float oneYearGrowthRate;
    private Float sinceThisYearGrowthRate;
    private Float sinceFoundedGrowthRate;
    private Float startAmount;

}
