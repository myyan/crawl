package stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stock.mapper.UserMapper;
import stock.po.User;

/**
 * Created by heiqie on 2017/2/9.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public int insert(User user){
        return mapper.insert(user);
    }
}
