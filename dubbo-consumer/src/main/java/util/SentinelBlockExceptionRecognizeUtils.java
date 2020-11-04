package util;

import me.dslztx.assist.util.ObjectAssist;
import me.dslztx.assist.util.StringAssist;

public class SentinelBlockExceptionRecognizeUtils {

    public static boolean isFlowException(RuntimeException e) {
        return ObjectAssist.isNotNull(e) && StringAssist.isNotBlank(e.getMessage())
            && e.getMessage().contains("SentinelBlockException: FlowException");
    }

    public static boolean isDegradeException(RuntimeException e) {
        return ObjectAssist.isNotNull(e) && StringAssist.isNotBlank(e.getMessage())
            && e.getMessage().contains("SentinelBlockException: DegradeException");
    }
}
