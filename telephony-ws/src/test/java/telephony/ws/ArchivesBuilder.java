package telephony.ws;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import telephony.ws.guice.TelephonyServletModule;
import telephony.ws.listener.TelephonyServletTestContextListener;
import telephony.ws.resource.PingResource;
import telephony.ws.resource.TestResource;
import telephony.ws.servlet.TelephonyRestletServlet;

public class ArchivesBuilder {

    public static final String ArchiveName = "telephony-ws";
    public static final String ArchiveExt = ".war";

    public static WebArchive createFirstWSTestWebArchive() {

        MavenDependencyResolver resolver = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml");

        WebArchive jar = ShrinkWrap
                .create(WebArchive.class, ArchiveName + ArchiveExt)
                .addClasses(TelephonyApplication.class, PingResource.class, TestResource.class)
                .addClasses(TelephonyServletTestContextListener.class, TelephonyServletModule.class, TelephonyRestletServlet.class)

                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet:2.1-SNAPSHOT").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.bitbucket.pawelhenek.telephony:telephony-core:0.1").goOffline().resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.inject.extensions:guice-servlet:3.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.inject.extensions:guice-persist:3.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.gwt:gwt-user:2.5.0").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.google.gwt:gwt-servlet:2.5.0").resolveAsFiles())

                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.crypto:2.1-SNAPSHOT").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.json:2.1-SNAPSHOT").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.xml:2.1-SNAPSHOT").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.restlet.jee:org.restlet.ext.servlet:2.1-SNAPSHOT").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.slf4j:slf4j-log4j12").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.slf4j:slf4j-api").resolveAsFiles())

                .addAsManifestResource("persistence.xml")
                .addAsWebResource("schema.sql")
                .addAsWebResource("test-data.sql")

                .setWebXML("web.xml");

        return jar;
    }
}
