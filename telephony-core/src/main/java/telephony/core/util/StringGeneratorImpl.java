package telephony.core.util;


import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public final class StringGeneratorImpl implements StringGenerator {

    /**
     * asd.
     */
    private static final int RANDOM_INT = 130;

    /**
     * asd.
     */
    private static final int HASH_LONG = 32;
    /**
     * asd.
     */
    private final SecureRandom random = new SecureRandom();

    /**
     * asd.
     * @return asd.
     */
    @Override
    public String nextSessionId() {
        return new BigInteger(RANDOM_INT, random).toString(HASH_LONG);
    }
}
