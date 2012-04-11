package war.server.guice.module;


import com.google.inject.AbstractModule;
import war.server.core.dao.implementations.*;
import war.server.core.dao.interfaces.*;
import war.server.core.service.implementations.*;
import war.server.core.service.interfaces.*;

/**
 * Guice DI module configuration
 */
public class TelephonyServerModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(UsersDao.class).to(UsersDaoImpl.class);
        bind(StoresDao.class).to(StoresDaoImpl.class);
        bind(ProductsDao.class).to(ProductsDaoImpl.class);
        bind(DeliveriesDao.class).to(DeliveriesDaoImpl.class);
        bind(SalesDao.class).to(SalesDaoImpl.class);

        bind(DeliveryService.class).to(DeliveryServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(StoreService.class).to(StoreServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(SaleService.class).to(SaleServiceImpl.class);
        bind(InformationService.class).to(InformationServiceImpl.class);
    }
}
