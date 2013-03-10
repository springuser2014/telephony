package telephony.ws;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

import telephony.ws.pre.TestsConfig;

/**
 * That's a simple test class, which tests if Arquillian and related stuff is
 * properly configured.
 */
@RunWith(Arquillian.class)
public class FirstWSTestz {

    private static final String TESTING_APP = TestsConfig.TESTING_HOST
                    + ArchivesBuilder.ARCHIVE_NAME + TestsConfig.REST_ADDR;

    /**
     * asd foo.
     * @return asd foo.
     */
    @Deployment
    @OverProtocol("Servlet 2.5")
    public static WebArchive createArchiveAndDeploy() {

        WebArchive jar = ArchivesBuilder.createFirstWSTestWebArchive();

        System.out.println(jar.toString(true));

        return jar;
    }

    @Resource(name = "alienName")
    private String alienName;

    /**
     * asd foo.
     */
    @Test
    public void first() {
        Assert.assertEquals("Connects to Tomcat and comparing its env settings", "Ike", alienName);
    }

    /**
     * asd foo.
     * @throws Exception asd foo.
     */
    @Test
    public void second() throws Exception {

        URL baseURL = new URL(TESTING_APP + "/test");
        ClientResource clientResource = new ClientResource(baseURL.toExternalForm());
        clientResource.setChallengeResponse(
            new ChallengeResponse(
                org.restlet.data.ChallengeScheme.HTTP_BASIC, "login", "secret".toCharArray()));

        clientResource.get();

//        doesn't work due to lack of resource's interface representation
//        assertTrue("response content contains 'alive' msg " + res, res.contains("value2"));
        assertTrue("reponse status is 200", clientResource.getStatus().equals(Status.SUCCESS_OK));
    }
}
