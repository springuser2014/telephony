package telephony.core.guice.env;

import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyCoreTestModule extends AbstractModule {
	
	public static final String PERSISTENCE_TEST = "telephony-test";
	private static final Integer SESSION_VALIDITY = new Integer( 30 * 60 * 1000);
	
	@Override
	protected void configure() {
		
		bind(Integer.class).annotatedWith(Names.named("sessionValidity")).toInstance(SESSION_VALIDITY);
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_TEST));
	}
}
