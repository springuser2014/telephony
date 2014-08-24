package telephony.ws.pre;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

/**
 * asd.
 */
public final class ArchivesBuilder {

    private ArchivesBuilder() {

    }

    public static final String ARCHIVE_NAME = "telephony-ws";

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
                    resolver.artifact("ch.qos.logback:logback-classic:1.0.7")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.slf4j:slf4j-api:1.7.5")
                    .resolveAsFiles())
                .addAsLibraries(
                    resolver.artifact("org.slf4j:slf4j-simple:1.7.5")
                    .resolveAsFiles())

                .addAsManifestResource("persistence.xml")
                .addAsManifestResource("logback.xml")

                .setWebXML("web.xml");

        return jar;
    }
}
