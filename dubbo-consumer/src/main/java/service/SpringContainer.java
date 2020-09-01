package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.dslztx.assist.util.ObjectAssist;
import me.dslztx.assist.util.StringAssist;

public class SpringContainer {

    private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

    private static ApplicationContext context = null;

    static {
        init();
    }

    private static void init() {
        try {
            context = new ClassPathXmlApplicationContext("applicationContext.xml");

            logger.info("load application context successfully");
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public static Object fetchBeanByName(String name) {
        if (ObjectAssist.isNull(context)) {
            throw new RuntimeException("no context loaded");
        }

        if (StringAssist.isBlank(name)) {
            throw new RuntimeException("illegal bean name");
        }

        return context.getBean(name);
    }

}
