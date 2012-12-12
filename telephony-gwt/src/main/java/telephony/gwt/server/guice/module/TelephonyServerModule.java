package telephony.gwt.server.guice.module;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.RequestProvider;
import com.gwtplatform.dispatch.server.guice.request.DefaultRequestProvider;
import telephony.core.service.impl.*;
import telephony.core.service.interfaces.*;

/**
 * Guice DI module configuration
 */
public class TelephonyServerModule extends AbstractModule {
    @Override
    protected void configure() {

        // binding dao layer objects

//        bind(UsersDao.class).to(UsersDaoImpl.class);
//        bind(StoresDao.class).to(StoresDaoImpl.class);
//        bind(ProductsDao.class).to(ProductsDaoImpl.class);
//        bind(DeliveriesDao.class).to(DeliveriesDaoImpl.class);
//        bind(SalesDao.class).to(SalesDaoImpl.class);
//        bind(RolesDao.class).to(RolesDaoImpl.class);
//        bind(UserStoresDao.class).to(UserStoresDaoImpl.class);
//        bind(UserRolesDao.class).to(UserRolesDaoImpl.class);
//        bind(SystemLogsDao.class).to(SystemLogsDaoImpl.class);


        // binding dao layer objects

        bind(DeliveryService.class).to(DeliveryServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(StoreService.class).to(StoreServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(SaleService.class).to(SaleServiceImpl.class);
        bind(InformationService.class).to(InformationServiceImpl.class);

        bind(RequestProvider.class).to(DefaultRequestProvider.class).in(Singleton.class);
//        bind(DispatchService.class).to(DispatchServiceImpl.class).in(Singleton.class);
    }
}
