package telephony.core.util;


import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Primary implementation of strings generator. 
 */
public final class StringGeneratorImpl implements StringGenerator {
    
    private static final int RANDOM_INT = 130;    
    private static final int HASH_LONG = 32;    
    private final SecureRandom random = new SecureRandom();

    /**
     * Generates session id.
     * @return 32-length random string.
     */
    @Override
    public String nextSessionId() {
        return new BigInteger(RANDOM_INT, random).toString(HASH_LONG);
    }
}
