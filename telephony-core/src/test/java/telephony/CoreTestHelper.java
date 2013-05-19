package telephony;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.googlecode.flyway.core.Flyway;

/**
 * asd.
 *
 */
public final class CoreTestHelper {
	
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "postgres";
	private static final String JDBC_CONNECTION_ADDR = 
			"jdbc:postgresql://localhost:5432/telephony-test";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CoreTestHelper.class);
	
	private CoreTestHelper() {
		
	}
	
	/**
     * asd.
     * @return asd.
     */
    @Provides
    @Singleton
    public static Flyway migratorProvider() {
    	
    	LOGGER.debug("TelephonyCoreTestModule.migratorProvider starts");
    	
    	Flyway m = new Flyway();
    	LOGGER.debug("TelephonyCoreTestModule.migratorProvider 1");
    	
    	// TODO : fetching connection params from persistence.xml or properties
    	DataSource dataSource = new SimpleDriverDataSource(
    			new org.postgresql.Driver(), 
    			JDBC_CONNECTION_ADDR, 
    			DB_USERNAME,
    			DB_PASSWORD
    	);
    	
    	m.setDataSource(dataSource);    
    	
    	
    	LOGGER.debug("TelephonyCoreTestModule.migratorProvider ends");
    	
    	return m;    	
    }

}
