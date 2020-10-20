package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;
import domain.Person;
import me.dslztx.assist.util.RandomAssist;

public class DubboClient {
    private static final Logger logger = LoggerFactory.getLogger(DubboClient.class);

    private static DubboService dubboService;

    static {
        init();
    }

    private static void init() {
        try {
            dubboService = (DubboService)SpringContainer.fetchBeanByName("dubboService");
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                Person person = new Person("dslztx", RandomAssist.randomInt(0, 100));

                DubboServiceResult result = dubboService.invoke(new DubboServiceQuery(person));

                logger.info(result.getMsg());
            }

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
    }
}
