package stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.mapper.TestMapper;
import stock.po.Test;

/**
 * Created by heiqie on 2017/2/13.
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public int insert(Test test) {
        return testMapper.insert(test);
    }

}
