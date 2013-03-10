package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import telephony.ws.resource.impl.ContactsResource;
import telephony.ws.resource.impl.DeliveriesResource;
import telephony.ws.resource.impl.HelloWorldResource;
import telephony.ws.resource.impl.RolesResource;
import telephony.ws.resource.impl.SalesResource;
import telephony.ws.resource.impl.SessionResource;
import telephony.ws.resource.impl.StoreProductsResource;
import telephony.ws.resource.impl.StoreRolesResource;
import telephony.ws.resource.impl.StoreUsersResource;
import telephony.ws.resource.impl.StoresResource;
import telephony.ws.resource.impl.UserRolesResource;
import telephony.ws.resource.impl.UserStoresResource;
import telephony.ws.resource.impl.UsersResource;

/**
 * Heart of telephony application.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class TelephonyApplication extends Application {

    /**
     * Registering all REST resources.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        // TODO : refactor *Resource classes, URLs as constants, extract Resources interfaces
        router.attach(SessionResource.URL, SessionResource.class);
        router.attach(UsersResource.URL, UsersResource.class);
        router.attach("/hello", HelloWorldResource.class);

        router.attach("/users/{id}", UsersResource.class);
        router.attach("/store/users/{id}", StoreUsersResource.class);
        router.attach("/store/products/{id}", StoreProductsResource.class);
        router.attach("/store/roles/{id}", StoreRolesResource.class);
        router.attach("/stores", StoresResource.class);
        router.attach("/stores/{id}", StoresResource.class);
        router.attach("/store/roles/{id}", StoreRolesResource.class);
        router.attach("/roles", RolesResource.class);
        router.attach("/roles/{id}", RolesResource.class);
        router.attach("/user/roles/{id}", UserRolesResource.class);
        router.attach("/user/stores/{id}", UserStoresResource.class);
        router.attach("/deliveries", DeliveriesResource.class);
        router.attach("/deliveries/{id}", DeliveriesResource.class);
        router.attach("/sales", SalesResource.class);
        router.attach("/sales/{id}", SalesResource.class);
        router.attach("/contacts", ContactsResource.class);
        router.attach("/contacts/{id}", ContactsResource.class);

        router.attach("/", new Directory(getContext(), "war:///"));

        return router;

    }
}
