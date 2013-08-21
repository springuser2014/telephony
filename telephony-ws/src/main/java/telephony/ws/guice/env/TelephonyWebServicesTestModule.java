package telephony.ws.guice.env;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.guice.env.Environment;
import telephony.core.guice.env.TelephonyCoreTestModule;
import telephony.ws.guice.TelephonyServletModule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.googlecode.flyway.core.Flyway;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyWebServicesTestModule extends AbstractModule {
	
	public  static final String PERSISTENCE_WS_TEST = "telephony-ws-test";
	private static final Integer SESSION_VALIDITY = new Integer(30 * 60 * 1000);

	@Override
	protected void configure() {		
		// TODO : refactor
		bind(Integer.class)
		.annotatedWith(Names.named("sessionValidity"))
		.toInstance(SESSION_VALIDITY);
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_WS_TEST));
		install(new TelephonyServletModule());		
	}
	
	 /**
     * asd.
     * @return asd.
     */
    @Provides
    @Singleton
    public Flyway migratorProvider() {
    	Flyway m = new Flyway();
    	
    	
    	// TODO : fetching connection params from persistence.xml or properties
    	DataSource dataSource = new SimpleDriverDataSource(
    			new org.postgresql.Driver(), 
    			"jdbc:postgresql://localhost:5432/" + PERSISTENCE_WS_TEST, 
    			"postgres",
    			"flyway"
    	);
    	
    	
    	m.setDataSource(dataSource);
    	m.setLocations(new String []{"db"});
    	m.migrate();
    	
    	return m;    	
    }
}
