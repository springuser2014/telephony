package telephony.ws;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import telephony.ws.guice.Log;
import telephony.ws.guice.SLF4JMembersJnjector;
import telephony.ws.guice.SLF4JTypeListener;
import telephony.ws.guice.TelephonyServletModule;
import telephony.ws.pre.TelephonyServletTestContextListener;
import telephony.ws.resource.SessionBean;
import telephony.ws.resource.impl.PingResource;
import telephony.ws.resource.impl.SessionResource;
import telephony.ws.resource.impl.TestResource;
import telephony.ws.servlet.TelephonyRestletServlet;

/**
 * foo bar.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public final class ArchivesBuilder {

    /**
     * asd foo.
     */
    private ArchivesBuilder() {

    }

    /**
     * foo bar.
     */
    public static final String ARCHIVE_NAME = "telephony-ws";

    /**
     * foo bar.
     */
    public static final String ARCHIVE_EXT = ".war";

    /**
     * foo bar.
     * @return foo bar.
     */
    public static WebArchive createFirstWSTestWebArchive() {

        MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");

        // TODO : refactor libraries dependencies - moves versions to constants
        WebArchive jar = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME + ARCHIVE_EXT)
                .addClasses(TelephonyApplication.class, PingResource.class, TestResource.class)
                .addClasses(TelephonyServletTestContextListener.class,
                    TelephonyServletModule.class, TelephonyRestletServlet.class)

                .addClasses(SLF4JTypeListener.class, SLF4JMembersJnjector.class, Log.class)

                .addClasses(SessionResource.class, SessionBean.class)

                .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet:2.1-SNAPSHOT")
                    .goOffline().resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.bitbucket.pawelhenek.telephony:telephony-core:0.7")
                    .goOffline().resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("com.google.inject.extensions:guice-servlet:3.0")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("com.google.inject.extensions:guice-persist:3.0")
                    .resolveAsFiles())
               
                .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet.ext.crypto:2.1-SNAPSHOT")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet.ext.json:2.1-SNAPSHOT")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet.ext.xml:2.1-SNAPSHOT")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet.ext.servlet:2.1-SNAPSHOT")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("ch.qos.logback:logback-classic:1.0.7").resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.slf4j:slf4j-api:1.7.5").resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.slf4j:slf4j-simple:1.7.5").resolveAsFiles())

		
                .addAsManifestResource("persistence.xml")
                .addAsWebResource("db/V2__Basic_schema_structure.sql")
                .addAsWebResource("db/V3__Preparation_to_use_hibernate_envers.sql")
                .addAsWebResource("db/V4__Test_data.sql")

                .setWebXML("web.xml");

        return jar;
    }
}
