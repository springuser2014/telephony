package telephony.core.guice.env;

import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyCoreTestModule extends AbstractModule {
	
	public static final String PERSISTENCE_TEST = "telephony-test";
	
	@Override
	protected void configure() {
		System.out.println("Configuring development");
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_TEST));
	}
}
