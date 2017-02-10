import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.po.Company;
import stock.po.User;
import stock.service.CompanyService;
import stock.service.UserService;

import java.util.Random;

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

        Random random = new Random();

        User user = User.builder()
                .name("test"+random.nextInt(10))
                .age(random.nextInt(20))
                .build();
        UserService service = (UserService) applicationContext.getBean("userService");
        int res = service.insert(user);
        System.out.println(res);

        CompanyService service2 = (CompanyService) applicationContext.getBean("companyService");
        Company company = Company.builder()
                .companyCode("600000")
                .companyName("浦发银行")
                .stockCode("600000")
                .industry("J")
                .build();
        int res2 = service2.insert(company);
        System.out.println(res2);

    }

    @Test
    public void test2(){
        CompanyService service = (CompanyService) applicationContext.getBean("companyService");
        Company company = Company.builder()
                .companyCode("600000")
                .companyName("浦发银行")
                .stockCode("600000")
                .industry("J")
                .build();
        service.insert(company);
    }
}