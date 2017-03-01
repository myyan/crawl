package stock.crawl.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by heiqie on 2017/3/1.
 */

public abstract class AbstractStrategy implements Strategy{

    @Autowired
    CacheService cacheService;

    public void startSession(){
        cacheService.startSession();
    }

    public void closeSession(){
        cacheService.closeSession();
    }

}
