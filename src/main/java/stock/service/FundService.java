package stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.mapper.FundMapper;
import stock.po.Fund;

/**
 * Created by heiqie on 2017/2/10.
 */
@Service
public class FundService {

    @Autowired
    private FundMapper mapper;

    public int insert(Fund fund){
        return mapper.insert(fund);
    }

}
