package telephony.core.assertion;

import java.util.Collection;

public final class CommonAssertions {

    public static <T> boolean isNull(T obj) {
        return obj == null;
    }

    public static <T> boolean isNotNull(T obj) {
        return obj != null;
    }

    public static boolean isEmpty(String obj) {
        return obj == null || obj.isEmpty();
    }

    public static boolean isNotEmpty(String obj) {
        return obj != null && !obj.isEmpty();
    }

    public static <T> boolean isEmpty(Collection<T> col) {
        return col == null || col.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> col) {
        return col != null && !col.isEmpty();
    }

    public static boolean isEmpty(Long n) {
        return n == null || n.equals(0l);
    }

    public static boolean isNotEmpty(Long n) {
        return n != null && !n.equals(0l);
    }

    public static boolean isEmpty(Integer n) {
        return n == null || n.equals(0);
    }

    public static boolean isNotEmpty(Integer n) {
        return n != null && !n.equals(0);
    }

}
