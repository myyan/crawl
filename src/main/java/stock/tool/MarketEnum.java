package stock.tool;

/**
 * Created by heiqie on 2017/2/15.
 */
public enum MarketEnum {

    Sh(0, "sh"),
    Sz(1, "sz");

    private int type;
    private String market;


    MarketEnum(int type, String market) {
        this.type = type;
        this.market = market;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public static String indexTypeOf(int type) {
        for (MarketEnum element : values()) {
            if (element.getType()==type){
                return element.getMarket();
            }
        }
        return null;
    }
}
