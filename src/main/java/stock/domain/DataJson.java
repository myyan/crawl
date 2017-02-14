package stock.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by heiqie on 2017/2/13.
 */
@Data
public class DataJson {
    @JsonProperty("t")
    private String t;

    @JsonProperty("p")
    private int p;

    @JsonProperty("total")
    private int total;

    @JsonProperty("l")
    private int l;

    @JsonProperty("o")
    private int o;

    @JsonProperty("data")
    private List<String> data1;
}
