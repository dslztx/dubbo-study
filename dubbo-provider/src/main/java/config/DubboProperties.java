package config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.dubbo.config.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
    private boolean server;

    @NestedConfigurationProperty
    private ApplicationConfig application;

    @NestedConfigurationProperty
    private ModuleConfig module;

    @NestedConfigurationProperty
    private RegistryConfig registry;

    @NestedConfigurationProperty
    private ProtocolConfig protocol;

    @NestedConfigurationProperty
    private MonitorConfig monitor;

    @NestedConfigurationProperty
    private ProviderConfig provider;

    @NestedConfigurationProperty
    private ConsumerConfig consumer;

    private Map<String, ApplicationConfig> applications = new LinkedHashMap<>();
    private Map<String, ModuleConfig> modules = new LinkedHashMap<>();
    private Map<String, RegistryConfig> registries = new LinkedHashMap<>();
    private Map<String, ProtocolConfig> protocols = new LinkedHashMap<>();
    private Map<String, MonitorConfig> monitors = new LinkedHashMap<>();
    private Map<String, ProviderConfig> providers = new LinkedHashMap<>();
    private Map<String, ConsumerConfig> consumers = new LinkedHashMap<>();

    public DubboProperties() {}

    public boolean isServer() {
        return this.server;
    }

    public void setServer(boolean server) {
        this.server = server;
    }

    public ApplicationConfig getApplication() {
        return this.application;
    }

    public void setApplication(ApplicationConfig application) {
        this.application = application;
    }

    public ModuleConfig getModule() {
        return this.module;
    }

    public void setModule(ModuleConfig module) {
        this.module = module;
    }

    public RegistryConfig getRegistry() {
        return this.registry;
    }

    public void setRegistry(RegistryConfig registry) {
        this.registry = registry;
    }

    public ProtocolConfig getProtocol() {
        return this.protocol;
    }

    public void setProtocol(ProtocolConfig protocol) {
        this.protocol = protocol;
    }

    public MonitorConfig getMonitor() {
        return this.monitor;
    }

    public void setMonitor(MonitorConfig monitor) {
        this.monitor = monitor;
    }

    public ProviderConfig getProvider() {
        return this.provider;
    }

    public void setProvider(ProviderConfig provider) {
        this.provider = provider;
    }

    public ConsumerConfig getConsumer() {
        return this.consumer;
    }

    public void setConsumer(ConsumerConfig consumer) {
        this.consumer = consumer;
    }

    public Map<String, ApplicationConfig> getApplications() {
        return this.applications;
    }

    public void setApplications(Map<String, ApplicationConfig> applications) {
        this.applications = applications;
    }

    public Map<String, ModuleConfig> getModules() {
        return this.modules;
    }

    public void setModules(Map<String, ModuleConfig> modules) {
        this.modules = modules;
    }

    public Map<String, RegistryConfig> getRegistries() {
        return this.registries;
    }

    public void setRegistries(Map<String, RegistryConfig> registries) {
        this.registries = registries;
    }

    public Map<String, ProtocolConfig> getProtocols() {
        return this.protocols;
    }

    public void setProtocols(Map<String, ProtocolConfig> protocols) {
        this.protocols = protocols;
    }

    public Map<String, MonitorConfig> getMonitors() {
        return this.monitors;
    }

    public void setMonitors(Map<String, MonitorConfig> monitors) {
        this.monitors = monitors;
    }

    public Map<String, ProviderConfig> getProviders() {
        return this.providers;
    }

    public void setProviders(Map<String, ProviderConfig> providers) {
        this.providers = providers;
    }

    public Map<String, ConsumerConfig> getConsumers() {
        return this.consumers;
    }

    public void setConsumers(Map<String, ConsumerConfig> consumers) {
        this.consumers = consumers;
    }

    @Override
    public String toString() {
        return "DubboProperties [server=" + this.server + ", application=" + this.application + ", module="
            + this.module + ", registry=" + this.registry + ", protocol=" + this.protocol + ", monitor=" + this.monitor
            + ", provider=" + this.provider + ", consumer=" + this.consumer + ", applications=" + this.applications
            + ", modules=" + this.modules + ", registries=" + this.registries + ", protocols=" + this.protocols
            + ", monitors=" + this.monitors + ", providers=" + this.providers + ", consumers=" + this.consumers + "]";
    }
}
