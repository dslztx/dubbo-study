package service;

import java.util.List;

import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;
import domain.Person;
import me.dslztx.assist.util.ConfigLoadAssist;
import me.dslztx.assist.util.RandomAssist;
import util.SentinelBlockExceptionRecognizeUtils;

public class DubboClient {
    private static final Logger logger = LoggerFactory.getLogger(DubboClient.class);

    private static final String CLUSTER_NAME = "dubboConsumer";

    private static final String NACOS_SERVER = "nacos.properties";

    private static DubboService dubboService;

    static {
        init();
    }

    private static void init() {
        try {
            dubboService = (DubboService)SpringContainer.fetchBeanByName("dubboService");

            registerSentinelDegradeRuleManager();

        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private static void registerSentinelDegradeRuleManager() {
        try {
            String groupId = "dubbo";

            String dataId = CLUSTER_NAME + "-degrade-rules";

            String servers = ConfigLoadAssist.propConfig(NACOS_SERVER).getString("nacos.servers");

            ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(servers,
                groupId, dataId, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {}));

            DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());

        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 100; i++) {
                Person person = new Person("dslztx", RandomAssist.randomInt(0, 100));

                try {
                    DubboServiceResult result = dubboService.invoke(new DubboServiceQuery(person));
                    logger.info(result.getMsg());
                } catch (RpcException e) {
                    logger.warn("", e);

                } catch (RuntimeException e) {
                    if (SentinelBlockExceptionRecognizeUtils.isFlowException(e)) {
                        logger.error("SentinelBlockException: FlowException");
                    } else if (SentinelBlockExceptionRecognizeUtils.isDegradeException(e)) {
                        logger.error("SentinelBlockException: DegradeException");
                    } else {
                        logger.error("", e);
                    }
                } catch (Exception e) {
                    logger.error("", e);
                }

            }

            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                logger.error("", e);
            }
        }
    }
}
