package telephony.ws;


import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;
import telephony.ws.resource.*;
import org.restlet.Application ;


/**
 * Simple restlet application
 */
public class TelephonyApplication extends Application {

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

        // some testing router binding
        router.attach(SessionResource.URL, SessionResource.class);

        router.attach("/", new Directory(getContext(), "war:///"));

//        router.attach("/users", UsersResource.class);
//        router.attach("/users/{id}", UsersResource.class);
//        router.attach("/store/users/{id}", StoreUsersResource.class);
//        router.attach("/store/products/{id}", StoreProductsResource.class);
//        router.attach("/store/roles/{id}", StoreRolesResource.class);
//        router.attach("/stores", StoresResource.class);
//        router.attach("/stores/{id}", StoresResource.class);
//        router.attach("/store/roles/{id}", StoreRolesResource.class);
//        router.attach("/roles", RolesResource.class);
//        router.attach("/roles/{id}", RolesResource.class);
//        router.attach("/user/roles/{id}", UserRolesResource.class);
//        router.attach("/user/stores/{id}", UserStoresResource.class);
//        router.attach("/deliveries", DeliveriesResource.class);
//        router.attach("/deliveries/{id}", DeliveriesResource.class);
//        router.attach("/sales", SalesResource.class);
//        router.attach("/sales/{id}", SalesResource.class);
//        router.attach("/contacts", ContactsResource.class);
//        router.attach("/contacts/{id}", ContactsResource.class);

        return router;

    }
}
