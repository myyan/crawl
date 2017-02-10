import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.mapper.UserMapper;
import stock.po.User;
import stock.service.UserService;

/**
 * Created by heiqie on 2017/2/10.
 */

public class UserTest {
    private ApplicationContext applicationContext;

    @Before
    public void setUp(){
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void test(){

        UserMapper mapper = (UserMapper) applicationContext.getBean("userMapper");
        User user = new User();
//        user.setId(1);
        user.setName("user");
        user.setAge(12);
        int result = mapper.insert(user);
        System.out.println(result);
        UserService service = (UserService) applicationContext.getBean("userService");
        int res = service.insert(user);
        System.out.println(res);

    }
}
