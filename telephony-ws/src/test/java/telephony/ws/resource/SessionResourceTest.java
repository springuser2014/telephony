package telephony.ws.resource;

import static junit.framework.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import telephony.ws.ArchivesBuilder;
import telephony.ws.pre.TestsConfig;
import telephony.ws.resource.impl.SessionResource;

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
    @Test
    public void second() throws Exception {

        URL baseURL = new URL(TESTING_APP + SessionResource.URL);
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
//        public static final String USER1_NAME = "user1@gmail.com";
//    	public static final String USER1_PASSWORD = "rfaysdhaiufsiuf";	

//        clientResource.setChallengeResponse(
//        new ChallengeResponse(
//        org.restlet.data.ChallengeScheme.HTTP_BASIC, "login", "secret".toCharArray()));
        JsonRepresentation repr = new JsonRepresentation(new SessionBean("user1@gmail.com", "rfaysdhaiufsiuf"));
        clientResource.post(repr);

//        doesn't work due to lack of resource's interface representation
//        assertTrue("response content contains 'alive' msg " + res, res.contains("value2"));
        assertTrue("reponse status is 200", clientResource.getStatus().equals(Status.SUCCESS_OK));
    }
}
