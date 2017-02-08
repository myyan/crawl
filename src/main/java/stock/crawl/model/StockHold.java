package stock.crawl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by heiqie on 2017/1/20.
 */
public class StockHold {
    @JsonProperty("Scode")
    private String SCode;

    @JsonProperty("SName")
    private String SName;

    @JsonProperty("RDate")
    private String RDate;

    @JsonProperty("LXDM")
    private String LXDM;

    @JsonProperty("LX")
    private String LX;

    @JsonProperty("Count")
    private int Count;

    @JsonProperty("CGChange")
    private String CGChange;

    @JsonProperty("ShareHDNum")
    private int ShareHDNum;

    @JsonProperty("VPosition")
    private double VPosition;

    @JsonProperty("TabRate")
    private double TabRate;

    @JsonProperty("LTZB")
    private double LTZB;

    @JsonProperty("ShareHDNumChange")
    private int ShareHDNumChange;

    @JsonProperty("RateChange")
    private double RateChange;

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public String getRDate() {
        return RDate;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }

    public String getLXDM() {
        return LXDM;
    }

    public void setLXDM(String LXDM) {
        this.LXDM = LXDM;
    }

    public String getLX() {
        return LX;
    }

    public void setLX(String LX) {
        this.LX = LX;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getCGChange() {
        return CGChange;
    }

    public void setCGChange(String CGChange) {
        this.CGChange = CGChange;
    }

    public int getShareHDNum() {
        return ShareHDNum;
    }

    public void setShareHDNum(int shareHDNum) {
        ShareHDNum = shareHDNum;
    }

    public double getVPosition() {
        return VPosition;
    }

    public void setVPosition(double VPosition) {
        this.VPosition = VPosition;
    }

    public double getTabRate() {
        return TabRate;
    }

    public void setTabRate(double tabRate) {
        TabRate = tabRate;
    }

    public double getLTZB() {
        return LTZB;
    }

    public void setLTZB(double LTZB) {
        this.LTZB = LTZB;
    }

    public int getShareHDNumChange() {
        return ShareHDNumChange;
    }

    public void setShareHDNumChange(int shareHDNumChange) {
        ShareHDNumChange = shareHDNumChange;
    }

    public double getRateChange() {
        return RateChange;
    }

    public void setRateChange(double rateChange) {
        RateChange = rateChange;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockHold stockHold = (StockHold) o;

        if (Count != stockHold.Count) return false;
        if (ShareHDNum != stockHold.ShareHDNum) return false;
        if (Double.compare(stockHold.VPosition, VPosition) != 0) return false;
        if (Double.compare(stockHold.TabRate, TabRate) != 0) return false;
        if (Double.compare(stockHold.LTZB, LTZB) != 0) return false;
        if (ShareHDNumChange != stockHold.ShareHDNumChange) return false;
        if (Double.compare(stockHold.RateChange, RateChange) != 0) return false;
        if (SCode != null ? !SCode.equals(stockHold.SCode) : stockHold.SCode != null) return false;
        if (SName != null ? !SName.equals(stockHold.SName) : stockHold.SName != null) return false;
        if (RDate != null ? !RDate.equals(stockHold.RDate) : stockHold.RDate != null) return false;
        if (LXDM != null ? !LXDM.equals(stockHold.LXDM) : stockHold.LXDM != null) return false;
        if (LX != null ? !LX.equals(stockHold.LX) : stockHold.LX != null) return false;
        return CGChange != null ? CGChange.equals(stockHold.CGChange) : stockHold.CGChange == null;
    }

    public StockHold() {
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = SCode != null ? SCode.hashCode() : 0;
        result = 31 * result + (SName != null ? SName.hashCode() : 0);
        result = 31 * result + (RDate != null ? RDate.hashCode() : 0);
        result = 31 * result + (LXDM != null ? LXDM.hashCode() : 0);
        result = 31 * result + (LX != null ? LX.hashCode() : 0);
        result = 31 * result + Count;
        result = 31 * result + (CGChange != null ? CGChange.hashCode() : 0);
        result = 31 * result + ShareHDNum;
        temp = Double.doubleToLongBits(VPosition);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(TabRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(LTZB);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + ShareHDNumChange;
        temp = Double.doubleToLongBits(RateChange);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "StockHold{" +
                "SCode='" + SCode + '\'' +
                ", SName='" + SName + '\'' +
                ", RDate='" + RDate + '\'' +
                ", LXDM='" + LXDM + '\'' +
                ", LX='" + LX + '\'' +
                ", Count=" + Count +
                ", CGChange='" + CGChange + '\'' +
                ", ShareHDNum=" + ShareHDNum +
                ", VPosition=" + VPosition +
                ", TabRate=" + TabRate +
                ", LTZB=" + LTZB +
                ", ShareHDNumChange=" + ShareHDNumChange +
                ", RateChange=" + RateChange +
                '}';
    }

    public StockHold(String SCode, String SName, String RDate, String LXDM, String LX, int count, String CGChange, int shareHDNum, double VPosition, double tabRate, double LTZB, int shareHDNumChange, double rateChange) {
        this.SCode = SCode;
        this.SName = SName;
        this.RDate = RDate;
        this.LXDM = LXDM;
        this.LX = LX;
        Count = count;
        this.CGChange = CGChange;
        ShareHDNum = shareHDNum;
        this.VPosition = VPosition;
        TabRate = tabRate;
        this.LTZB = LTZB;
        ShareHDNumChange = shareHDNumChange;
        RateChange = rateChange;
    }

}
