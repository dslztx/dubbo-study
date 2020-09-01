package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;
import domain.Person;

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
        for (int i = 0; i < 10; i++) {
            Person person = new Person("dslztx", i);

            DubboServiceResult result = dubboService.invoke(new DubboServiceQuery(person));

            logger.info(result.getMsg());
        }
    }
}
