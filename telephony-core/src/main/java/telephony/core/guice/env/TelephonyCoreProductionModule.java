package telephony.core.guice.env;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import telephony.core.guice.TelephonyCoreServicesModule;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.googlecode.flyway.core.Flyway;

/**
 * asd.
 *
 */
@Environment("PRODUCTION")
public class TelephonyCoreProductionModule  extends AbstractModule {
	
	public  static final String PERSISTENCE = "telephony";
	private static final Integer SESSION_VALIDITY = new Integer(30 * 60 * 1000);

	/**
	 * asd.
	 */
	protected void configure() {
		bind(Integer.class)
		.annotatedWith(Names.named("sessionValidity"))
		.toInstance(SESSION_VALIDITY);
		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE));
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
    			"jdbc:postgresql://localhost:5432/telephony", 
    			"postgres",
    			"postgres"
    	);
    	
    	
    	m.setDataSource(dataSource);
    	m.migrate();
    	
    	return m;    	
    }

}
