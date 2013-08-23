package telephony.ws;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

import telephony.core.guice.env.EnvironmentNameResolver;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.guice.Log;
import telephony.ws.guice.SLF4JMembersJnjector;
import telephony.ws.guice.SLF4JTypeListener;
import telephony.ws.guice.TelephonyServletModule;
import telephony.ws.guice.env.TelephonyWebServicesEnvironmentResolver;
import telephony.ws.guice.env.TelephonyWebServicesProductionModule;
import telephony.ws.guice.env.TelephonyWebServicesTestModule;
import telephony.ws.pre.TelephonyServletTestContextListener;
import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.SessionBean;
import telephony.ws.resource.SessionResource;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.impl.ContactsResourceImpl;
import telephony.ws.resource.impl.DeliveriesResourceImpl;
import telephony.ws.resource.impl.HelloWorldResourceImpl;
import telephony.ws.resource.impl.PingResourceImpl;
import telephony.ws.resource.impl.RolesResourceImpl;
import telephony.ws.resource.impl.SalesResourceImpl;
import telephony.ws.resource.impl.SessionResourceImpl;
import telephony.ws.resource.impl.StoreProductsResourceImpl;
import telephony.ws.resource.impl.StoreRolesResourceImpl;
import telephony.ws.resource.impl.StoreUsersResourceImpl;
import telephony.ws.resource.impl.StoresResourceImpl;
import telephony.ws.resource.impl.TestResourceImpl;
import telephony.ws.resource.impl.UserRolesResourceImpl;
import telephony.ws.resource.impl.UserStoresResourceImpl;
import telephony.ws.resource.impl.UsersResourceImpl;
import telephony.ws.servlet.TelephonyRestletServlet;

/**
 * foo bar.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public final class ArchivesBuilder {

    private ArchivesBuilder() {

    }

    /**
     * foo bar.
     */
    public static final String ARCHIVE_NAME = "telephony-ws-0.8";

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
              
                .addPackage("telephony.ws")
                .addPackage("telephony.ws.guice")
                .addPackage("telephony.ws.guice.env")
                .addPackage("telephony.ws.resource")
                .addPackage("telephony.ws.resource.impl")
                .addPackage("telephony.ws.servlet")                
                .addPackage("telephony.ws.pre")
                			
               .addAsLibraries(
                    resolver.artifact("org.restlet.jee:org.restlet:2.1-SNAPSHOT")
                    .goOffline().resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.bitbucket.pawelhenek.telephony:telephony-core:0.8")
                    .goOffline().resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("com.google.inject.extensions:guice-servlet:3.0")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("com.google.inject.extensions:guice-persist:3.0")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.hibernate:hibernate-entitymanager:3.5.6-Final")
                    .resolveAsFiles())       
                .addAsLibraries(
                    resolver.artifact("org.hibernate:hibernate-core:3.5.6-Final")
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
                .addAsManifestResource("logback.xml")

                .setWebXML("web.xml");

        return jar;
    }
}
