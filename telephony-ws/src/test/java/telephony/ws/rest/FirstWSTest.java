package telephony.ws.rest;

import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import telephony.ws.Application;
import telephony.ws.guice.TelephonyServletModule;
import telephony.ws.resource.PingResource;
import telephony.ws.resource.TestResource;
import telephony.ws.rest.listener.TelephonyServletTestContextListener;
import telephony.ws.servlet.TelephonyRestletServlet;

import javax.annotation.Resource;

import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class FirstWSTest {


    @Deployment
    @OverProtocol("Servlet 2.5")
    public static WebArchive createArchiveAndDeploy() {

        MavenDependencyResolver resolver = DependencyResolvers
                    .use(MavenDependencyResolver.class)
                    .loadMetadataFromPom("pom.xml");

        WebArchive jar = ShrinkWrap
                .create(WebArchive.class, "telephony-ws.war")
                .addClasses(Application.class, PingResource.class, TestResource.class)
                .addClasses(TelephonyServletTestContextListener.class, TelephonyServletModule.class, TelephonyRestletServlet.class)

                .addAsLibraries(resolver.artifact("org.bitbucket.pawelhenek.telephony:telephony-core:0.1").goOffline().resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.inject.extensions:guice-servlet").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.inject.extensions:guice-persist:3.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.gwt:gwt-user:2.5.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.gwt:gwt-servlet:2.5.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.crypto").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.json").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.xml").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.servlet").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.slf4j:slf4j-log4j12").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.slf4j:slf4j-api").resolveAsFiles())

                .addAsManifestResource("persistence.xml")
                .addAsWebResource("schema.sql")
                .addAsWebResource("test-data.sql")

                .setWebXML("web.xml");

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
