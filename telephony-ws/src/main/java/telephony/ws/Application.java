package telephony.ws;


import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
import telephony.ws.resource.PingResource;
import telephony.ws.resource.TestResource;


/**
 * Simple restlet application
 */
public class Application extends org.restlet.Application {
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        router.attach("/ping", PingResource.class);
        router.attach("/test", TestResource.class);

        // Resource guarded with BASIC scheme
        ChallengeAuthenticator guard = new ChallengeAuthenticator(getContext(),
                ChallengeScheme.HTTP_BASIC, "Sample GWT app - basic");
        MapVerifier verifier = new MapVerifier();
        verifier.getLocalSecrets().put("scott", "tiger".toCharArray());
        guard.setVerifier(verifier);
        guard.setNext(PingResource.class);
        router.attach("/guarded", guard);

        // Resource guarded with DIGEST scheme
        DigestAuthenticator dGuard = new DigestAuthenticator(getContext(),
                "Sample GWT app - digest", "serverKey");
        // Load a single static login/password pair.
        verifier = new MapVerifier();
        verifier.getLocalSecrets().put("login", "secret".toCharArray());
        dGuard.setWrappedVerifier(verifier);
        dGuard.setNext(PingResource.class);
        router.attach("/guarded_digest", dGuard);

        router.attach("/", new Directory(getContext(), "war:///"));
        return router;

    }
}
