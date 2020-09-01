package service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;
import me.dslztx.assist.util.ObjectAssist;

// todo
@Service
public class DubboServiceImpl implements DubboService {

    private static final Logger logger = LoggerFactory.getLogger(DubboServiceImpl.class);

    private volatile AtomicInteger counter = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000 * 60)
    public synchronized void stat() {
        // 避免Dubbo起来即停止

        AtomicInteger oldCounter = counter;

        counter = new AtomicInteger(0);

        logger.info("request num [{}] in 1 minute", oldCounter.get());
    }

    @PostConstruct
    public void init() {
        logger.info("init Dubbo Service instance");
    }

    @Override
    public DubboServiceResult invoke(DubboServiceQuery query) {
        counter.incrementAndGet();

        if (ObjectAssist.isNull(query) || ObjectAssist.isNull(query.getPerson())) {
            throw new RuntimeException("illegal query argument");
        }

        logger.info("receive query : {}", query.toString());

        String msg = "success";

        DubboServiceResult result = new DubboServiceResult();
        result.setMsg(msg);

        return result;
    }
}
