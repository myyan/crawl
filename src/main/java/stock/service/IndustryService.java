package stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.mapper.IndustryMapper;
import stock.po.Industry;

import java.util.List;

/**
 * Created by heiqie on 2017/2/14.
 */
@Service
public class IndustryService {

    @Autowired
    private IndustryMapper mapper;

    public int insert(Industry industry) {
        return mapper.insert(industry);
    }

    public int insertAll(List<Industry> industryList){
        return mapper.insertAll(industryList);
    }

}
