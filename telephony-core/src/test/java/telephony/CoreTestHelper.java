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
	
	protected static final Flyway migrator 	= new Flyway();
	
	private CoreTestHelper() {
		
	}
	
	static {
		
		LOGGER.debug("TelephonyCoreTestModule.migratorProvider starts");
	
    	// TODO : fetching connection params from persistence.xml or properties
    	DataSource dataSource = new SimpleDriverDataSource(
    			new org.postgresql.Driver(), 
    			JDBC_CONNECTION_ADDR, 
    			DB_USERNAME,
    			DB_PASSWORD
    	);
    	
    	migrator.setDataSource(dataSource);
    	
    	LOGGER.debug("TelephonyCoreTestModule.migratorProvider ends");
    	
	}
	
	/**
     * asd.
     * @return asd.
     */
    @Provides
    @Singleton
    public static Flyway migratorProvider() {
    	
    	return migrator;    	
    }

}
