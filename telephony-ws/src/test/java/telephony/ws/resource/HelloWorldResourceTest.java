package telephony.ws.resource;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import telephony.ws.pre.ArchivesBuilder;
import telephony.ws.test.TestsConfig;

/**
 * asd.
 */
@RunWith(JUnit4ClassRunner.class)
public class HelloWorldResourceTest extends BaseWSTest {

	private static final String TESTING_APP = TestsConfig.TESTING_HOST
			+ ArchivesBuilder.ARCHIVE_NAME + TestsConfig.REST_ADDR;

	@SuppressWarnings("deprecation")
	@Test
	public void first() throws Exception {

		URL baseURL = new URL(TESTING_APP + HelloWorldResource.URL);
		ClientResource clientResource = new ClientResource(
				baseURL.toExternalForm());
		HelloWorldResource res = clientResource.wrap(HelloWorldResource.class);

		JsonRepresentation rep = res.hello();

		assertNotNull("returned json != null", rep != null);

		assertTrue("response should contains 'hello'",
				rep.getText().contains("hello"));

		assertTrue("response status is 200", clientResource.getResponse()
				.getStatus().equals(Status.SUCCESS_OK));

	}
}
