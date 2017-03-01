package stock.crawl.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.net.URL;

/**
 * Created by heiqie on 2017/2/16.
 */
public class Example {

    @Test
    public void test(){

        Jedis jedis = new Jedis("127.0.0.1",16379);       // 连接到Redis服务器
        jedis.set("greeting", "Hello, world!");     // 将字符串缓存到Redis服务器
        System.out.println(jedis.get("greeting") + "========");  // 从Redis缓存中获取数据
        jedis.set("name", "欢迎来到Redis缓存！");
        String name= jedis.get("name");
        System.out.print(name+"===========");
    }
}
