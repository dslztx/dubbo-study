package app;

import java.util.List;

import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import config.DubboProperties;
import me.dslztx.assist.util.ConfigLoadAssist;
import service.DubboService;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"service", "config"})
public class DubboApplication {

    private static final Logger logger = LoggerFactory.getLogger(DubboApplication.class);

    private static final String CLUSTER_NAME = "dubboProducer";

    private static final String NACOS_SERVER = "nacos.properties";

    public static void main(String[] args) {

        logger.info("Dubbo Application is starting");

        SpringApplication.run(DubboApplication.class);

        logger.info("Dubbo Application has started");
    }

    @Bean
    public DubboBootstrap dubboBootstrap(DubboProperties dubboProperties, DubboService dubboService) {

        logger.info("dubbo starting : {}", dubboProperties);

        ServiceConfig<DubboService> service = new ServiceConfig<>();
        service.setInterface(DubboService.class);
        service.setRef(dubboService);

        DubboBootstrap bootstrap = DubboBootstrap.getInstance();

        bootstrap.application(dubboProperties.getApplication());

        bootstrap.registry(dubboProperties.getRegistry());

        bootstrap.protocol(dubboProperties.getProtocol());

        bootstrap.provider(dubboProperties.getProvider());
        bootstrap.service(service);

        bootstrap.start();

        logger.info("dubbo start finished");

        registerSentinelFlowRuleManager();

        return bootstrap;
    }

    private void registerSentinelFlowRuleManager() {
        try {
            String groupId = "dubbo";

            String dataId = CLUSTER_NAME + "-flow-rules";

            String servers = ConfigLoadAssist.propConfig(NACOS_SERVER).getString("nacos.servers");

            ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(servers, groupId,
                dataId, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {}));
            FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(3);
        taskScheduler.initialize();
        return taskScheduler;
    }

}
