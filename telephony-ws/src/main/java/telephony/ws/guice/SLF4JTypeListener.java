
package telephony.ws.guice;

import java.lang.reflect.Field;

import org.slf4j.Logger;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SLF4JTypeListener implements TypeListener {

    /**
     * asd.
     * @param typeLiteral asd.
     * @param typeEncounter asd.
     * @param <T> asd.
     */
    @Override
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {

        for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
            if (field.getType() == Logger.class && field.isAnnotationPresent(Log.class)) {
                typeEncounter.register(new SLF4JMembersJnjector<T>(field));
            }
        }
    }

}
