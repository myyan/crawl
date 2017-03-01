import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import stock.po.Company;
import stock.po.Fund;
import stock.po.User;
import stock.service.CompanyService;
import stock.service.FundService;
import stock.service.TestService;
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



    }

    @Test
    public void test2(){

    }

    @Test
    public void testFund(){
        FundService service = (FundService) applicationContext.getBean("fundService");
        Fund fund = new Fund();
        fund.setFundCode("200000");
        fund.setFundName("新安");
//        fund.setId(12);

//        fund.setStartAmount(.12f);
        int result = service.insert(fund);
        System.out.println(result);
    }

    @Test
    public void testT(){
        TestService testService = (TestService) applicationContext.getBean("testService");
        stock.po.Test test = new stock.po.Test();
        test.setPrice(123);
        int result = testService.insert(test);
        System.out.println(result);
    }
}
