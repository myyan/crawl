package stock.crawl.module;

import org.springframework.stereotype.Service;

/**
 * Created by heiqie on 2017/3/1.
 */
@Service
public class CacheService {

    public void startSession(){
        System.out.println("cache service start session");
    }

    public void closeSession(){
        System.out.println("cache service close session");
    }
}
