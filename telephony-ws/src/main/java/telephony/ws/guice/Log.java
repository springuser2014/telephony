package telephony.ws.guice;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Scope;

/**
 * asd.
 */
@Scope
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Log {

}