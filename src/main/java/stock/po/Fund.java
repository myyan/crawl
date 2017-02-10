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
    private String dailyGrowthRate;
    private String monthlyGrowthRate;
}
