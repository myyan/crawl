package stock.crawl.module;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by heiqie on 2017/3/1.
 */
@Service
public class ConvesationStrategy extends AbstractStrategy {
    @Override
    public void doStrategy() {
        startSession();
        System.out.println("conversation strategy");

    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ConvesationStrategy convesationStrategy = context.getBean(ConvesationStrategy.class);
        convesationStrategy.doStrategy();
    }

}

