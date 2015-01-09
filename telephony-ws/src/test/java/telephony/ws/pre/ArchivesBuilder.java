package telephony.ws.pre;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;

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

        MavenResolverSystem resolver = Maven.resolver();

        // TODO : refactor libraries dependencies - moves versions to constants
        
        WebArchive jar = ShrinkWrap
                .create(WebArchive.class, ARCHIVE_NAME + ARCHIVE_EXT)
                .addPackage("telephony.ws")
                .addPackage("telephony.ws.guice")
                .addPackage("telephony.ws.guice.env")
                .addPackage("telephony.ws.json")
                .addPackage("telephony.ws.resource")
                .addPackage("telephony.ws.resource.impl")

                .addPackage("telephony.ws.resource.complaint")
                .addPackage("telephony.ws.resource.complaint.impl")

                .addPackage("telephony.ws.resource.contact")
                .addPackage("telephony.ws.resource.contact.impl")

                .addPackage("telephony.ws.resource.delivery")
                .addPackage("telephony.ws.resource.delivery.impl")

                .addPackage("telephony.ws.resource.products")
                .addPackage("telephony.ws.resource.products.impl")

                .addPackage("telephony.ws.resource.role")
                .addPackage("telephony.ws.resource.role.impl")

                .addPackage("telephony.ws.resource.sale")
                .addPackage("telephony.ws.resource.sale.impl")

                .addPackage("telephony.ws.resource.session")
                .addPackage("telephony.ws.resource.session.impl")

                .addPackage("telephony.ws.resource.store")
                .addPackage("telephony.ws.resource.store.impl")

                .addPackage("telephony.ws.resource.tax")
                .addPackage("telephony.ws.resource.tax.impl")

                .addPackage("telephony.ws.resource.taxes")
                .addPackage("telephony.ws.resource.taxes.impl")

                .addPackage("telephony.ws.resource.user")
                .addPackage("telephony.ws.resource.user.impl")

                .addPackage("telephony.ws.servlet")
                .addPackage("telephony.ws.shiro")
                .addPackage("telephony.ws.test")
                .addPackage("telephony.ws.pre")

                .addAsLibraries(
                        resolver.resolve("org.bitbucket.pawelhenek.telephony:telephony-core:0.8")
                                .withTransitivity().asFile()
                )
                .addAsLibraries(
                        resolver.resolve("com.google.inject.extensions:guice-servlet:3.0")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("com.google.inject.extensions:guice-persist:3.0")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.hibernate:hibernate-entitymanager:3.5.6-Final")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.hibernate:hibernate-core:3.5.6-Final")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet.ext.crypto:2.2-SNAPSHOT")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet.ext.json:2.2-SNAPSHOT")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet.ext.xml:2.2-SNAPSHOT")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet.ext.servlet:2.2-SNAPSHOT")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet.ext.jackson:2.2.2")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.restlet.jee:org.restlet:2.2-SNAPSHOT")
//                                .exclusion("org.codehaus.groovy:groovy")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("org.codehaus.groovy:groovy-all:2.3.7")
                                .withTransitivity().asFile())
//                .addAsLibraries(
//                        resolver.resolve("ch.qos.logback:logback-classic:1.0.7")
//                                .withTransitivity().asFile())
//                .addAsLibraries(
//                        resolver.resolve("org.slf4j:slf4j-api:1.6.0")
//                                .withTransitivity().asFile())
//                .addAsLibraries(
//                        resolver.resolve("org.slf4j:slf4j-simple:1.6.0")
//                                .withTransitivity().asFile())
//                .addAsLibraries(
//                    resolver.resolve("org.slf4j:slf4j-log4j12:1.6.1")
//                        .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("com.google.code.gson:gson:2.3")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("com.jayway.restassured:rest-assured:2.4.0")
                                .withTransitivity().asFile())
                .addAsLibraries(
                        resolver.resolve("com.jayway.restassured:json-path:2.4.0")
                                .withTransitivity().asFile())

                .addAsManifestResource("persistence.xml")
//                .addAsManifestResource("logback.xml")
                .addAsResource("logging.properties")

                .setWebXML("web.xml");

        return jar;
    }
}
