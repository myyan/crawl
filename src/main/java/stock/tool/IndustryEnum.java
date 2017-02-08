package stock.tool;

/**
 * Created by heiqie on 2017/2/8.
 */
public enum  IndustryEnum {
    ;
    private String industryCode;
    private String industryName;

    IndustryEnum(String industryCode, String industryName) {
        this.industryCode = industryCode;
        this.industryName = industryName;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }
}
