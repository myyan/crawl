package stock.crawl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by heiqie on 2017/2/8.
 */

public class FundData {
    @JsonProperty("chars")
    private String[] chars;

    @JsonProperty("datas")
    private List<FundData> datas;

    @JsonProperty("count")
    private String[] count;

    @JsonProperty("record")
    private String record;

    @JsonProperty("pages")
    private String pages;

    @JsonProperty("curpage")
    private String curpage;

    @JsonProperty("indexsy")
    private float[] indexsy;

    @JsonProperty("showday")
    private String[] showday;


}
