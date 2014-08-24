package telephony.ws.resource;

import org.jboss.shrinkwrap.api.spec.WebArchive;

import telephony.ws.pre.ArchivesBuilder;
import telephony.ws.test.TestsConfig;

/**
 * asd.
 */
public abstract class BaseWSTest {

	protected static final String TESTING_APP = TestsConfig.TESTING_HOST
	                    + ArchivesBuilder.ARCHIVE_NAME + TestsConfig.REST_ADDR;

	/**
	 * foo bar.
	 * @return foo bar.
	 */
	public static WebArchive createArchiveAndDeploy() {
	
		WebArchive jar = ArchivesBuilder.createFirstWSTestWebArchive();
	
		System.out.println(jar.toString(true));
	
		return jar;
	}

	/**
	 * asd.
	 */
	public BaseWSTest() {
		super();
	}

}