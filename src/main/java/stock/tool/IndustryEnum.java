package stock.tool;

/**
 * Created by heiqie on 2017/2/9.
 * @description : CSRC行业分类
 */
public enum IndustryEnum {
    A("A", "农、林、牧、渔业"),
    B("B", "采矿业"),
    C("C", "制造业"),
    D("D", "电力、热力、燃气及水生产和供应业"),
    E("E", "建筑业"),
    F("F", "批发和零售业"),
    G("G", "交通运输、仓储和邮政业"),
    H("H", "住宿和餐饮业"),
    I("I", "信息传输、软件和信息技术服务业"),
    J("J", "金融业"),
    K("K", "房地产业"),
    L("L", "租赁和商务服务业"),
    M("M", "科学研究和技术服务业"),
    N("N", "水利、环境和公共设施管理业"),
    O("O", "居民服务、修理和其他服务业"),
    P("P", "教育"),
    Q("Q", "卫生和社会工作"),
    R("R", "文化、体育和娱乐业"),
    S("S", "综合");


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

    public static String industryNameOf(String industryName) {
        for (IndustryEnum item : values()) {
            if (item.getIndustryName().equals(industryName)){
                return item.getIndustryCode();
            }
        }
        return null;
    }
}
