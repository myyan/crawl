package stock.po;

import lombok.Builder;
import lombok.Data;

/**
 * Created by heiqie on 2017/1/18.
 */
@Data
@Builder
public class Company {
    private int id;
    private String companyCode;
    private String companyName;
    private String stockCode;
    private String industry;
 }
