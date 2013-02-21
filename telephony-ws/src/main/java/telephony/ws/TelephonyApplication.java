package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import telephony.ws.resource.ContactsResource;
import telephony.ws.resource.DeliveriesResource;
import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.RolesResource;
import telephony.ws.resource.SalesResource;
import telephony.ws.resource.SessionResource;
import telephony.ws.resource.StoreProductsResource;
import telephony.ws.resource.StoreRolesResource;
import telephony.ws.resource.StoreUsersResource;
import telephony.ws.resource.StoresResource;
import telephony.ws.resource.UserRolesResource;
import telephony.ws.resource.UserStoresResource;
import telephony.ws.resource.UsersResource;


/**
 * @author gam3r
 * Simple restlet application
 */
public class TelephonyApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        // some testing router binding
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
