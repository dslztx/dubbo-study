import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.fastjson.JSON;

public class Main {
    public static void main(String[] args) {
        DegradeRule rule = new DegradeRule();

        rule.setResource("service.DubboService:invoke(domain.DubboServiceQuery)");

        rule.setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType());
        rule.setCount(0.3D);
        rule.setTimeWindow(60);
        rule.setMinRequestAmount(5);
        rule.setStatIntervalMs(60 * 1000);

        System.out.println(JSON.toJSONString(rule));
    }
}
