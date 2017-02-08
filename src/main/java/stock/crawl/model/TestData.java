package stock.crawl.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by heiqie on 2017/2/8.
 */
public class TestData {
    @JsonProperty("pages")
    private int pages;
    @JsonProperty("name")
    private String name;

    @JsonProperty("info")
    private int[] info;

    @Override
    public String toString() {
        return "TestData{" +
                "pages=" + pages +
                ", name='" + name + '\'' +
                ", info=" + Arrays.toString(info) +
                '}';
    }
}
