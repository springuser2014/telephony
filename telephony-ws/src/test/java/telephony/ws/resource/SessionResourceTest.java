package telephony.ws.resource;

import static junit.framework.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.Client;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import telephony.ws.ArchivesBuilder;
import telephony.ws.pre.TestsConfig;
import telephony.ws.resource.impl.SessionResourceImpl;

/**
 * foo bar.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@RunWith(Arquillian.class)
public class SessionResourceTest {

    private static final String TESTING_APP = TestsConfig.TESTING_HOST
                    + ArchivesBuilder.ARCHIVE_NAME + TestsConfig.REST_ADDR;

    /**
     * foo bar.
     * @return foo bar.
     */
    @Deployment
    @OverProtocol("Servlet 2.5")
    public static WebArchive createArchiveAndDeploy() {

        WebArchive jar = ArchivesBuilder.createFirstWSTestWebArchive();

        System.out.println(jar.toString(true));

        return jar;
    }
    
    /**
     * foo bar.
     * @throws Exception foo bar.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void first() throws Exception {

        URL baseURL = new URL(TESTING_APP + HelloWorldResource.URL);
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
        
        HelloWorldResource res = clientResource.wrap(HelloWorldResource.class);        
        clientResource.get();
        
        assertTrue("response status is 200", 
        		clientResource
        		.getResponse()
        		.getStatus().equals(Status.SUCCESS_OK));
    }

    /**
     * foo bar.
     * @throws Exception foo bar.
     */
    @SuppressWarnings("deprecation")
	@Test
    public void second() throws Exception {

        URL baseURL = new URL(TESTING_APP + SessionResourceImpl.URL);
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
        
        SessionResource res = clientResource.wrap(SessionResource.class);
        
        JsonRepresentation repr = new JsonRepresentation(
        		new SessionBean("user1@gmail.com", "rfaysdhaiufsiuf")
        );
        clientResource.post(repr);

        assertTrue("response status is 200", 
        		clientResource
        		.getResponse()
        		.getStatus().equals(Status.SUCCESS_OK));
    }
}


