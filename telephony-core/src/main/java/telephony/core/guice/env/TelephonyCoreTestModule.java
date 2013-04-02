package telephony.core.guice.env;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.googlecode.flyway.core.Flyway;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyCoreTestModule extends AbstractModule {
	
	public static final String PERSISTENCE_TEST = "telephony-test";
	
	private final Logger logger = LoggerFactory.getLogger(getClass());	        
	
	@Override
	protected void configure() {
		System.out.println("Configuring development");
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_TEST));
	}
	
	 // TODO : move below method to another module
    /**
     * asd.
     * @return asd.
     */
    @Provides
    @Singleton
    public Flyway migratorProvider() {
    	
    	logger.debug("TelephonyCoreTestModule.migratorProvider starts");
    	
    	Flyway m = new Flyway();
    	
    	
    	// TODO : fetching connection params from persistence.xml or properties
    	DataSource dataSource = new SimpleDriverDataSource(
    			new org.postgresql.Driver(), 
    			"jdbc:postgresql://localhost:5432/telephony-test", 
    			"postgres",
    			"postgres"
    	);
    	
    	
    	m.setDataSource(dataSource);
    	
    	m.clean();
    	
    	m.init();
    	
    	m.migrate();
    	
    	logger.debug("TelephonyCoreTestModule.migratorProvider ends");
    	
    	return m;    	
    }
}
