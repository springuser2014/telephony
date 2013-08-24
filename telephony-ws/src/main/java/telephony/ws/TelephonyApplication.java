package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.googlecode.flyway.core.Flyway;

import telephony.ws.resource.ContactsResource;
import telephony.ws.resource.DeliveriesModificationResource;
import telephony.ws.resource.DeliveriesResource;
import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.RolesModificationResource;
import telephony.ws.resource.SalesModificationResource;
import telephony.ws.resource.SalesResource;
import telephony.ws.resource.StoreProductsResource;
import telephony.ws.resource.StoreRolesResource;
import telephony.ws.resource.StoreUsersResource;
import telephony.ws.resource.StoresModificationResource;
import telephony.ws.resource.StoresResource;
import telephony.ws.resource.UserRolesResource;
import telephony.ws.resource.UserStoresResource;
import telephony.ws.resource.UsersModificationResource;
import telephony.ws.resource.UsersResource;
import telephony.ws.resource.impl.ContactsModificationResource;
import telephony.ws.resource.impl.ContactsModificationResourceImpl;
import telephony.ws.resource.impl.ContactsResourceImpl;
import telephony.ws.resource.impl.DeliveriesModificationResourceImpl;
import telephony.ws.resource.impl.DeliveriesResourceImpl;
import telephony.ws.resource.impl.HelloWorldResourceImpl;
import telephony.ws.resource.impl.RolesModificationResourceImpl;
import telephony.ws.resource.impl.RolesResource;
import telephony.ws.resource.impl.RolesResourceImpl;
import telephony.ws.resource.impl.SalesModificationResourceImpl;
import telephony.ws.resource.impl.SalesResourceImpl;
import telephony.ws.resource.impl.SessionResourceImpl;
import telephony.ws.resource.impl.StoreProductsResourceImpl;
import telephony.ws.resource.impl.StoreRolesResourceImpl;
import telephony.ws.resource.impl.StoreUsersResourceImpl;
import telephony.ws.resource.impl.StoresModificationResourceImpl;
import telephony.ws.resource.impl.StoresResourceImpl;
import telephony.ws.resource.impl.UserRolesResourceImpl;
import telephony.ws.resource.impl.UserStoresResourceImpl;
import telephony.ws.resource.impl.UsersModificationResourceImpl;
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
        
        router.attach(HelloWorldResource.URL, HelloWorldResourceImpl.class);
        
        router.attach(SessionResourceImpl.URL, SessionResourceImpl.class);
        router.attach(UsersResource.URL, UsersResourceImpl.class);
        router.attach(UsersModificationResource.URL, UsersModificationResourceImpl.class);
        router.attach(StoreUsersResource.URL, StoreUsersResourceImpl.class);
        router.attach(StoreProductsResource.URL, StoreProductsResourceImpl.class);
        router.attach(StoreRolesResource.URL, StoreRolesResourceImpl.class);
        router.attach(StoresResource.URL, StoresResourceImpl.class);
        router.attach(StoresModificationResource.URL, StoresModificationResourceImpl.class);
        router.attach(StoreRolesResource.URL, StoreRolesResourceImpl.class);
        router.attach(RolesResource.URL, RolesResourceImpl.class);
        router.attach(RolesModificationResource.URL, RolesModificationResourceImpl.class);
        router.attach(UserRolesResource.URL, UserRolesResourceImpl.class);
        router.attach(UserStoresResource.URL, UserStoresResourceImpl.class);
        router.attach(DeliveriesResource.URL, DeliveriesResourceImpl.class);
        router.attach(DeliveriesModificationResource.URL, DeliveriesModificationResourceImpl.class);
        router.attach(SalesResource.URL, SalesResourceImpl.class);
        router.attach(SalesModificationResource.URL, SalesModificationResourceImpl.class);
        router.attach(ContactsResource.URL, ContactsResourceImpl.class);
        router.attach(ContactsModificationResource.URL, ContactsModificationResourceImpl.class);

        router.attach("/", new Directory(getContext(), "war:///"));

        return router;

    }
}
