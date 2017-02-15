package stock.mapper;

import stock.po.Industry;

import java.util.List;

/**
 * Created by heiqie on 2017/2/14.
 */
public interface IndustryMapper {
    int insert(Industry industry);

    int insertAll(List<Industry> industryList);
}
