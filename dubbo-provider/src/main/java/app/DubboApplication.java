package app;

import org.apache.dubbo.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import config.DubboProperties;
import service.DubboService;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"service", "config"})
public class DubboApplication {

    private static final Logger logger = LoggerFactory.getLogger(DubboApplication.class);

    public static void main(String[] args) {

        logger.info("Dubbo Application is starting");

        SpringApplication.run(DubboApplication.class);

        logger.info("Dubbo Application has started");
    }

    @Bean
    public ServiceConfig<DubboService> dubboServiceConfig(DubboProperties dubboProperties, DubboService dubboService) {

        logger.info("dubbo service config : {}", dubboProperties);

        ServiceConfig<DubboService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(dubboProperties.getApplication());
        serviceConfig.setProtocol(dubboProperties.getProtocol());
        serviceConfig.setRegistry(dubboProperties.getRegistry());
        serviceConfig.setProvider(dubboProperties.getProvider());

        serviceConfig.setInterface(DubboService.class);

        serviceConfig.setRef(dubboService);
        serviceConfig.export();

        // todo consumer的属性没有用

        return serviceConfig;
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
