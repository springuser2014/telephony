package telephony.core.util;

/**
 * Representation of strings generator.
 */
public interface StringGenerator {
    /**
     * Generates random string which can be used as password, hash etc.
     * 
     * @return random string
     */
    String nextSessionId();
}
