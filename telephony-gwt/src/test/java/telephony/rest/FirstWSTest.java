package telephony.rest;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;
import java.net.URL;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class FirstWSTest {


    @Deployment
    public static WebArchive createArchiveAndDeploy() {
        WebArchive jar = ShrinkWrap
                .create(WebArchive.class, "telephony-gwt.war")
                .addPackage("org.apache.commons.codec.binary");

//                .addClasses(Application.class, PingResource.class, TestResource.class)
//                .setWebXML("web.xml")
//                .addAsResource("persistence.xml");

        System.out.println(jar.toString(true));

        return jar;
    }

//    @ArquillianResource
//    URL deploymentUrl;

    @Resource(name = "alienName")
    String alienName;

    @Test
    public void first() {
        Assert.assertEquals("Ike", alienName);
        assertTrue(true);
    }




}
