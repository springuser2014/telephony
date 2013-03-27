package telephony.ws.guice.env;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.guice.env.Environment;
import telephony.ws.guice.TelephonyServletModule;
import telephony.ws.pre.TelephonyServletTestContextListener;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyWebServicesTestModule extends AbstractModule {
	
	public static final String PERSISTENCE_WS_TEST = "telephony-ws-test";
	
	@Override
	protected void configure() {		
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_WS_TEST));
		install(new TelephonyServletModule());
	}
}
