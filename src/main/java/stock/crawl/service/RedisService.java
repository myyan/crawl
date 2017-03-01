package stock.crawl.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import stock.po.User;
import stock.service.CompanyService;

/**
 * Created by heiqie on 2017/2/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-redis.xml"})
public class RedisService {

    Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;


    @Autowired
    private RedisTemplate template;

    @Autowired
    private CompanyService companyService;


    @Test
    public void testContainer(){
        assert(companyService!=null);
    }
    @Test
    public void test() {


        String name = jedisConnectionFactory.getHostName();
        log.info("name:{}",name);
        String passwd = jedisConnectionFactory.getPassword();
        log.info("passwd:{}",passwd);
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setAge(12);
        ValueOperations<String, User> valueOps = template.opsForValue();
        String key = "user:"+user.getId();
        valueOps.set(key,user);
        assert (jedisConnectionFactory != null);

        User user1  = valueOps.get(key);
        log.info("user :{}",user1);
        User user2 = valueOps.get(2);
        System.out.println(user2);

    }

    @Test
    public void test2(){
//        String key = "sessionStatePO:"+"wabot";
//        ValueOperations<String, Object> valueOps = template.opsForValue();
//        Object o = valueOps.get(key);
//        log.info("info:{}",o);
    }

}
