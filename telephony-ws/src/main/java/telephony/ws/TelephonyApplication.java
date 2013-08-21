package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.googlecode.flyway.core.Flyway;

import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.impl.ContactsResourceImpl;
import telephony.ws.resource.impl.DeliveriesResourceImpl;
import telephony.ws.resource.impl.HelloWorldResourceImpl;
import telephony.ws.resource.impl.RolesResourceImpl;
import telephony.ws.resource.impl.SalesResourceImpl;
import telephony.ws.resource.impl.SessionResourceImpl;
import telephony.ws.resource.impl.StoreProductsResourceImpl;
import telephony.ws.resource.impl.StoreRolesResourceImpl;
import telephony.ws.resource.impl.StoreUsersResourceImpl;
import telephony.ws.resource.impl.StoresResourceImpl;
import telephony.ws.resource.impl.UserRolesResourceImpl;
import telephony.ws.resource.impl.UserStoresResourceImpl;
import telephony.ws.resource.impl.UsersResourceImpl;

/**
 * Heart of telephony application.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class TelephonyApplication extends Application {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Starts the migration process.
	 * @param migrator Migrator object defined within core module.
	 */
	@Inject
	public TelephonyApplication(Flyway migrator) {
		
		logger.info("Migration starting..");
		
		logger.info("Number of executed migrations : " + Integer.toString(migrator.migrate()));
	}
	
    /**
     * Registering all REST resources.
     * @return asd.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());

        // TODO : refactor *Resource classes, URLs as constants, extract Resources interfaces
        router.attach(SessionResourceImpl.URL, SessionResourceImpl.class);
        router.attach(UsersResourceImpl.URL, UsersResourceImpl.class);
        router.attach(HelloWorldResource.URL, HelloWorldResourceImpl.class);

        router.attach("/users/{id}", UsersResourceImpl.class);
        router.attach("/store/users/{id}", StoreUsersResourceImpl.class);
        router.attach("/store/products/{id}", StoreProductsResourceImpl.class);
        router.attach("/store/roles/{id}", StoreRolesResourceImpl.class);
        router.attach("/stores", StoresResourceImpl.class);
        router.attach("/stores/{id}", StoresResourceImpl.class);
        router.attach("/store/roles/{id}", StoreRolesResourceImpl.class);
        router.attach("/roles", RolesResourceImpl.class);
        router.attach("/roles/{id}", RolesResourceImpl.class);
        router.attach("/user/roles/{id}", UserRolesResourceImpl.class);
        router.attach("/user/stores/{id}", UserStoresResourceImpl.class);
        router.attach("/deliveries", DeliveriesResourceImpl.class);
        router.attach("/deliveries/{id}", DeliveriesResourceImpl.class);
        router.attach("/sales", SalesResourceImpl.class);
        router.attach("/sales/{id}", SalesResourceImpl.class);
        router.attach("/contacts", ContactsResourceImpl.class);
        router.attach("/contacts/{id}", ContactsResourceImpl.class);

        router.attach("/", new Directory(getContext(), "war:///"));

        return router;

    }
}
