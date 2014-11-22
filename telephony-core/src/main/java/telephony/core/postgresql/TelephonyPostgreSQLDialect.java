package telephony.core.postgresql;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQLDialect;

/**
 * asd.
 */
public class TelephonyPostgreSQLDialect extends PostgreSQLDialect {
	
	/**
	 * asd.
	 */
	public TelephonyPostgreSQLDialect() {
		super();
		
		registerColumnType(Types.DOUBLE, "double precision");
	}

}
