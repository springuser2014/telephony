package telephony.ws.guice.env;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.guice.env.Environment;
import telephony.core.guice.env.TelephonyCoreTestModule;
import telephony.ws.guice.TelephonyServletModule;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyWebServicesTestModule extends AbstractModule {
	
	public  static final String PERSISTENCE_WS_TEST = "telephony-ws-test";
	private static final Integer SESSION_VALIDITY = new Integer(30 * 60 * 1000);

	@Override
	protected void configure() {		
		
		bind(Integer.class)
		.annotatedWith(Names.named("sessionValidity"))
		.toInstance(SESSION_VALIDITY);
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_WS_TEST));
		install(new TelephonyServletModule());
		
	}
	
	
}
