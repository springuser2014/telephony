package telephony.ws.guice;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 *
 * @author gam3r
 *
 */
public class MiscModule extends AbstractModule {



	/**
	 * asd.
	 */
    @Override
    protected final void configure() {
        bindListener(Matchers.any(), new SLF4JTypeListener());
    }
}
