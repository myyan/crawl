package stock.crawl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by heiqie on 2017/1/20.
 */
public class JsonData {
    @JsonProperty("pages")
    private int pages;

    @JsonProperty("data")
    private List<StockHold> data;

    public JsonData(int pages, List<StockHold> data) {
        this.pages = pages;
        this.data = data;
    }

    public JsonData() {
    }

    @Override
    public String toString() {
        return "DataJson{" +
                "pages=" + pages +
                ", data=" + data +
                '}';
    }
}
