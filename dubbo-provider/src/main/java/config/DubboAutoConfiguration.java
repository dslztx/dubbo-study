package config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.*;

@Configuration
@EnableConfigurationProperties({DubboProperties.class})
public class DubboAutoConfiguration {
    public DubboAutoConfiguration() {}

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig applicationConfig(DubboProperties dubboProperties) {
        return dubboProperties.getApplication();
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig registryConfig(DubboProperties dubboProperties) {
        return dubboProperties.getRegistry();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProviderConfig providerConfig(DubboProperties dubboProperties) {
        return dubboProperties.getProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConsumerConfig consumerConfig(DubboProperties dubboProperties) {
        return dubboProperties.getConsumer();
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig protocolConfig(DubboProperties dubboProperties) {
        return dubboProperties.getProtocol();
    }

    @Bean
    @ConditionalOnMissingBean
    public ModuleConfig moduleConfig(DubboProperties dubboProperties) {
        return dubboProperties.getModule();
    }

    @Bean
    @ConditionalOnMissingBean
    public MonitorConfig monitorConfig(DubboProperties dubboProperties) {
        return dubboProperties.getMonitor();
    }
}
