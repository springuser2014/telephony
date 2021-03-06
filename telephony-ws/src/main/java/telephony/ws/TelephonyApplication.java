package telephony.ws;


import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.HelloWorldResourceImpl;
import telephony.ws.resource.complaint.*;
import telephony.ws.resource.complaint.impl.*;
import telephony.ws.resource.contact.*;
import telephony.ws.resource.contact.impl.*;
import telephony.ws.resource.delivery.*;
import telephony.ws.resource.delivery.impl.*;
import telephony.ws.resource.products.*;
import telephony.ws.resource.products.impl.*;
import telephony.ws.resource.sale.*;
import telephony.ws.resource.sale.impl.*;
import telephony.ws.resource.session.*;
import telephony.ws.resource.session.impl.*;
import telephony.ws.resource.store.*;
import telephony.ws.resource.store.impl.*;
import telephony.ws.resource.tax.TaxAddResource;
import telephony.ws.resource.tax.TaxDeleteResource;
import telephony.ws.resource.tax.TaxEditResource;
import telephony.ws.resource.tax.impl.TaxAddResourceImpl;
import telephony.ws.resource.tax.impl.TaxDeleteResourceImpl;
import telephony.ws.resource.tax.impl.TaxEditResourceImpl;
import telephony.ws.resource.taxes.TaxesFetchResource;
import telephony.ws.resource.taxes.impl.TaxesFetchResourceImpl;
import telephony.ws.resource.user.*;
import telephony.ws.resource.user.impl.*;

/**
 * Heart of the application.
 */
public class TelephonyApplication extends Application {	
	
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
        
        // sessions resources - complete
        router.attach(SessionInitializationResource.URL, SessionInitializationResourceImpl.class);
        router.attach(SessionValidationResource.URL, SessionValidationResourceImpl.class);
        router.attach(SessionRefreshResource.URL, SessionRefreshResourceImpl.class);
        router.attach(SessionDestroyResource.URL, SessionDestoryResourceImpl.class);
        router.attach(SessionDetailsResource.URL, SessionDetailsResourceImpl.class);
        
        // users resources - complete
        router.attach(UsersAddResource.URL, UsersAddResourceImpl.class);
        router.attach(UsersFetchResource.URL, UsersFetchResourceImpl.class);
        router.attach(UsersEditResource.URL, UsersEditResourceImpl.class);
        router.attach(UsersChangePasswordResource.URL, UsersChangePasswordResourceImpl.class);
        router.attach(UsersDeleteResource.URL, UsersDeleteResourceImpl.class);
        router.attach(UsersDetailsResource.URL, UsersDetailsResourceImpl.class);
        
        // deliveries resources - complete
        router.attach(DeliveryAddResource.URL, DeliveryAddResourceImpl.class);
        router.attach(DeliveriesFetchResource.URL, DeliveriesFetchResourceImpl.class);
        router.attach(DeliveriesEditResource.URL, DeliveriesEditResourceImpl.class);
        router.attach(DeliveriesDeleteResource.URL, DeliveriesDeleteResourceImpl.class);
        router.attach(DeliveriesDetailsResource.URL, DeliveriesDetailsResourceImpl.class);

        // deliveries resources - complete
        router.attach(SalesAddResource.URL, SalesAddResourceImpl.class);
        router.attach(SalesFetchResource.URL, SalesFetchResourceImpl.class);
        router.attach(SalesEditResource.URL, SalesEditResourceImpl.class);
        router.attach(SalesDeleteResource.URL, SalesDeleteResourceImpl.class);
        router.attach(SalesDetailsResource.URL, SalesDetailsResourceImpl.class);
        
        // products resources - complete
        router.attach(ProductsFetchResource.URL, ProductsFetchResourceImpl.class);
        router.attach(ProductsDetailsResource.URL, ProductsDetailsResourceImpl.class);
        router.attach(ProductEditResource.URL, ProductEditResourceImpl.class);

        router.attach(ProductCheckImeiResource.URL, ProductCheckImeiResourceImpl.class);
        router.attach(ProductFetchDataResource.URL, ProductFetchDataResourceImpl.class);
        
        // taxes resource - complete
        router.attach(TaxAddResource.URL, TaxAddResourceImpl.class);
        router.attach(TaxesFetchResource.URL, TaxesFetchResourceImpl.class);
        router.attach(TaxEditResource.URL, TaxEditResourceImpl.class);
        router.attach(TaxDeleteResource.URL, TaxDeleteResourceImpl.class);
        
        // contacts resources - complete 
        router.attach(ContactsFetchResource.URL, ContactsFetchResourceImpl.class);
        router.attach(ContactsDeleteResource.URL, ContactsDeleteResourceImpl.class);
        router.attach(ContactsAddResource.URL, ContactsAddResourceImpl.class);
        router.attach(ContactsEditResource.URL, ContactsEditResourceImpl.class);
        router.attach(ContactsDetailsResource.URL, ContactsDetailsResourceImpl.class);
        
        // complaints resources complete
        router.attach(ProductComplaintAddResource.URL, ProductComplaintAddResourceImpl.class);
        router.attach(ProductComplaintEditResource.URL, ProductComplaintEditResourceImpl.class);
        router.attach(ProductComplaintFetchResource.URL, ProductComplaintFetchResourceImpl.class);
        router.attach(ProductComplaintDetailsResource.URL, ProductComplaintDetailsResourceImpl.class);

        router.attach(SaleComplaintAddResource.URL, SaleComplaintAddResourceImpl.class);
        router.attach(SaleComplaintEditResource.URL, SaleComplaintEditResourceImpl.class);
        router.attach(SaleComplaintFetchResource.URL, SaleComplaintFetchResourceImpl.class);
        router.attach(SaleComplaintDetailsResource.URL, SaleComplaintDetailsResourceImpl.class);

        router.attach(ComplaintChangeStatusAsAcceptedResource.URL, ComplaintChangeStatusAsAcceptedResourceImpl.class);
        router.attach(ComplaintChangeStatusAsInProgressResource.URL, ComplaintChangeStatusAsInProgressResourceImpl.class);
        router.attach(ComplaintChangeStatusAsRejectedResource.URL, ComplaintChangeStatusAsRejectedResourceImpl.class);
        router.attach(ComplaintChangeStatusAsResolvedResource.URL, ComplaintChangeStatusAsResolvedResourceImpl.class);

        router.attach(ComplaintDeleteResource.URL, ComplaintDeleteResourceImpl.class);
        router.attach(ComplaintAddCommentResource.URL, ComplaintAddCommentResourceImpl.class);
        router.attach(ComplaintAddAnonymousCommentResource.URL, ComplaintAddAnonymousCommentResourceImpl.class);
        
        // TODO verify the stuff below
        router.attach(StoreFetchUsersResource.URL, StoreFetchUsersResourceImpl.class);
        router.attach(StoreFetchProductsResource.URL, StoreFetchProductsResourceImpl.class);
        router.attach(StoresAddResource.URL, StoresAddResourceImpl.class);
        router.attach(StoresFetchResource.URL, StoresFetchResourceImpl.class);
        router.attach(StoresEditResource.URL, StoresEditResourceImpl.class);
        router.attach(StoresDeleteResource.URL, StoresDeleteResourceImpl.class);  
        router.attach(StoreSetRolesResource.URL, StoreSetRolesResourceImpl.class);

        router.attach(UserAddRolesResource.URL, UserAddRolesResourceImpl.class);
        router.attach(UserAddStoresResource.URL, UserAddStoresResourceImpl.class);
        router.attach(UserDeleteRolesResource.URL, UserDeleteRolesResourceImpl.class);
        router.attach(UserDeleteStoresResource.URL, UserDeleteStoresResourceImpl.class);
        // TODO verify the stuff above

        router.attach("/", new Directory(getContext(), "war:///"));

        return router;
    }
}
