package telephony.ws.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.MembersInjector;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 * @param <T> asd.
 */
public class SLF4JMembersJnjector<T> implements MembersInjector<T> {

    private final Field field;
    private final Logger logger;

    /**
     * asd.
     * @param field asd.
     */
    public SLF4JMembersJnjector(Field field) {
        this.field = field;
        this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    /**
     * asd.
     * @param instance asd.
     *
     */
    @Override
    public void injectMembers(T instance) {
        try {
            field.set(instance, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }




}
