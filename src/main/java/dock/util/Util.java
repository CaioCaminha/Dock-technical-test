package dock.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static boolean isNull(String str) {
        return str == null;
    }

    public static boolean isNull(Boolean value) {
        return value == null;
    }

    public static boolean isNull(Integer value) {
        return value == null;
    }

    public static boolean isNull(Long value) {
        return value == null;
    }

    public static boolean isNull(Double value) {
        return value == null;
    }

    public static boolean isNull(BigDecimal value) {
        return value == null;
    }

    public static boolean isNull(Date value) {
        return value == null;
    }

    public static boolean isNullOrBlank(String param) {
        if (isNull(param) || param.trim().length() == 0) {
            return true;
        }
        return false;
    }


}
