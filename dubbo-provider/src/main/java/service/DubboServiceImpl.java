package service;

import domain.DubboServiceQuery;
import domain.DubboServiceResult;
import me.dslztx.assist.util.ObjectAssist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class DubboServiceImpl implements DubboService {

    private static final Logger logger = LoggerFactory.getLogger(DubboServiceImpl.class);

    private AtomicInteger counter = new AtomicInteger(0);

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    @Scheduled(fixedRate = 1000 * 60)
    public synchronized void stat() {
        // 避免Dubbo起来即停止

        rwLock.writeLock().lock();
        try {
            AtomicInteger oldCounter = counter;

            counter = new AtomicInteger(0);

            logger.info("request num [{}] in 1 minute", oldCounter.get());
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @PostConstruct
    public void init() {
        logger.info("init Dubbo Service instance successfully");
    }

    @Override
    public DubboServiceResult invoke(DubboServiceQuery query) {
        rwLock.readLock().lock();
        try {
            counter.incrementAndGet();

            return invoke0(query);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    private DubboServiceResult invoke0(DubboServiceQuery query) {
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
