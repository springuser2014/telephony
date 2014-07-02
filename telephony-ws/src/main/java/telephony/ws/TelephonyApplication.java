package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.resource.Post;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.googlecode.flyway.core.Flyway;

import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.HelloWorldResourceImpl;
import telephony.ws.resource.contact.ContactsAddResource;
import telephony.ws.resource.contact.ContactsDeleteResource;
import telephony.ws.resource.contact.ContactsFetchResource;
import telephony.ws.resource.contact.impl.ContactsAddResourceImpl;
import telephony.ws.resource.contact.impl.ContactsDeleteResourceImpl;
import telephony.ws.resource.contact.impl.ContactsFetchResourceImpl;
import telephony.ws.resource.delivery.DeliveriesAddResource;
import telephony.ws.resource.delivery.DeliveriesDeleteResource;
import telephony.ws.resource.delivery.DeliveriesEditResource;
import telephony.ws.resource.delivery.DeliveriesFetchResource;
import telephony.ws.resource.delivery.impl.DeliveriesAddResourceImpl;
import telephony.ws.resource.delivery.impl.DeliveriesDeleteResourceImpl;
import telephony.ws.resource.delivery.impl.DeliveriesEditResourceImpl;
import telephony.ws.resource.delivery.impl.DeliveriesFetchResourceImpl;
import telephony.ws.resource.role.RolesDeleteResource;
import telephony.ws.resource.role.RolesAddResource;
import telephony.ws.resource.role.impl.RolesAddResourceImpl;
import telephony.ws.resource.role.impl.RolesDeleteResourceImpl;
import telephony.ws.resource.sale.SalesAddResource;
import telephony.ws.resource.sale.SalesEditResource;
import telephony.ws.resource.sale.SalesFetchResource;
import telephony.ws.resource.sale.impl.SalesAddResourceImpl;
import telephony.ws.resource.sale.impl.SalesDeleteResource;
import telephony.ws.resource.sale.impl.SalesDeleteResourceImpl;
import telephony.ws.resource.sale.impl.SalesEditResourceImpl;
import telephony.ws.resource.sale.impl.SalesFetchResourceImpl;
import telephony.ws.resource.session.SessionDestroyResource;
import telephony.ws.resource.session.SessionRefreshResource;
import telephony.ws.resource.session.SessionInitializationResource;
import telephony.ws.resource.session.SessionValidationResource;
import telephony.ws.resource.session.impl.SessionDestoryResourceImpl;
import telephony.ws.resource.session.impl.SessionInitializationResourceImpl;
import telephony.ws.resource.session.impl.SessionRefreshResourceImpl;
import telephony.ws.resource.session.impl.SessionValidationResourceImpl;
import telephony.ws.resource.store.StoreFetchProductsResource;
import telephony.ws.resource.store.StoreSetRolesResource;
import telephony.ws.resource.store.StoreFetchUsersResource;
import telephony.ws.resource.store.StoresAddResource;
import telephony.ws.resource.store.StoresDeleteResource;
import telephony.ws.resource.store.StoresEditResource;
import telephony.ws.resource.store.StoresFetchResource;
import telephony.ws.resource.store.impl.StoreFetchProductsResourceImpl;
import telephony.ws.resource.store.impl.StoreFetchUsersResourceImpl;
import telephony.ws.resource.store.impl.StoreSetRolesResourceImpl;
import telephony.ws.resource.store.impl.StoresAddResourceImpl;
import telephony.ws.resource.store.impl.StoresDeleteResourceImpl;
import telephony.ws.resource.store.impl.StoresEditResourceImpl;
import telephony.ws.resource.store.impl.StoresFetchResourceImpl;
import telephony.ws.resource.user.UserAddRolesResource;
import telephony.ws.resource.user.UserAddStoresResource;
import telephony.ws.resource.user.UserDeleteRolesResource;
import telephony.ws.resource.user.UserDeleteStoresResource;
import telephony.ws.resource.user.UsersDeleteResource;
import telephony.ws.resource.user.UsersEditResource;
import telephony.ws.resource.user.UsersFetchResource;
import telephony.ws.resource.user.impl.UserAddRolesResourceImpl;
import telephony.ws.resource.user.impl.UserAddStoresResourceImpl;
import telephony.ws.resource.user.impl.UserDeleteRolesResourceImpl;
import telephony.ws.resource.user.impl.UserDeleteStoresResourceImpl;
import telephony.ws.resource.user.impl.UsersAddResource;
import telephony.ws.resource.user.impl.UsersAddResourceImpl;
import telephony.ws.resource.user.impl.UsersDeleteResourceImpl;
import telephony.ws.resource.user.impl.UsersEditResourceImpl;
import telephony.ws.resource.user.impl.UsersFetchResourceImpl;

/**
 * Heart of telephony application.
 */
public class TelephonyApplication extends Application {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public TelephonyApplication() {
		
	}
	
	/**
	 * Starts the migration process.
	 * @param migrator Migrator object defined within core module.
	 */
//	@Inject
//	public TelephonyApplication(Flyway migrator) {
//		
//		logger.info("Migration starting..");
//		
//		logger.info("Number of executed migrations : " + Integer.toString(migrator.migrate()));
//	}
	
    /**
     * Registering all REST resources.
     * @return asd.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        router.attach(HelloWorldResource.URL, HelloWorldResourceImpl.class);
        
        router.attach(SessionInitializationResource.URL, SessionInitializationResourceImpl.class);
        router.attach(SessionValidationResource.URL, SessionValidationResourceImpl.class);
        router.attach(SessionRefreshResource.URL, SessionRefreshResourceImpl.class);
        router.attach(SessionDestroyResource.URL, SessionDestoryResourceImpl.class);
        
        router.attach(UsersAddResource.URL, UsersAddResourceImpl.class);
        router.attach(UsersFetchResource.URL, UsersFetchResourceImpl.class);
        router.attach(UsersEditResource.URL, UsersEditResourceImpl.class);
        router.attach(UsersDeleteResource.URL, UsersDeleteResourceImpl.class);
        
        router.attach(StoreFetchUsersResource.URL, StoreFetchUsersResourceImpl.class);
        router.attach(StoreFetchProductsResource.URL, StoreFetchProductsResourceImpl.class);
        router.attach(StoresAddResource.URL, StoresAddResourceImpl.class);
        router.attach(StoresFetchResource.URL, StoresFetchResourceImpl.class);
        router.attach(StoresEditResource.URL, StoresEditResourceImpl.class);
        router.attach(StoresDeleteResource.URL, StoresDeleteResourceImpl.class);  
        router.attach(StoreSetRolesResource.URL, StoreSetRolesResourceImpl.class);
         
        router.attach(RolesAddResource.URL, RolesAddResourceImpl.class);
        router.attach(RolesDeleteResource.URL, RolesDeleteResourceImpl.class);
        
        router.attach(UserAddRolesResource.URL, UserAddRolesResourceImpl.class);
        router.attach(UserAddStoresResource.URL, UserAddStoresResourceImpl.class);
        router.attach(UserDeleteRolesResource.URL, UserDeleteRolesResourceImpl.class);
        router.attach(UserDeleteStoresResource.URL, UserDeleteStoresResourceImpl.class);
        
        router.attach(DeliveriesAddResource.URL, DeliveriesAddResourceImpl.class);
        router.attach(DeliveriesFetchResource.URL, DeliveriesFetchResourceImpl.class);
        router.attach(DeliveriesEditResource.URL, DeliveriesEditResourceImpl.class);
        router.attach(DeliveriesDeleteResource.URL, DeliveriesDeleteResourceImpl.class);
        
        router.attach(SalesAddResource.URL, SalesAddResourceImpl.class);
        router.attach(SalesFetchResource.URL, SalesFetchResourceImpl.class);
        router.attach(SalesEditResource.URL, SalesEditResourceImpl.class);
        router.attach(SalesDeleteResource.URL, SalesDeleteResourceImpl.class);
        
        router.attach(ContactsFetchResource.URL, ContactsFetchResourceImpl.class);
        router.attach(ContactsDeleteResource.URL, ContactsDeleteResourceImpl.class);
        router.attach(ContactsAddResource.URL, ContactsAddResourceImpl.class);
        
        router.attach("/", new Directory(getContext(), "war:///"));

        return router;

    }
}
