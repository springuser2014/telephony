package telephony.core.guice;

import com.google.inject.AbstractModule;
import telephony.core.dao.implementations.SystemLogsDaoImpl;
import telephony.core.dao.implementations.*;
import telephony.core.dao.interfaces.*;
import telephony.core.service.impl.*;
import telephony.core.service.interfaces.*;

/**
 * Provides some default bindings for DAOs and services
 */
public class TelephonyCoreServicesModule extends AbstractModule {

    @Override
    protected void configure() {

        // binding dao layer objects

        bind(UsersDao.class).to(UsersDaoImpl.class);
        bind(StoresDao.class).to(StoresDaoImpl.class);
        bind(ProductsDao.class).to(ProductsDaoImpl.class);
        bind(DeliveriesDao.class).to(DeliveriesDaoImpl.class);
        bind(SalesDao.class).to(SalesDaoImpl.class);
        bind(RolesDao.class).to(RolesDaoImpl.class);
        bind(UserStoresDao.class).to(UserStoresDaoImpl.class);
        bind(UserRolesDao.class).to(UserRolesDaoImpl.class);
        bind(SystemLogsDao.class).to(SystemLogsDaoImpl.class);
        bind(ContactsDao.class).to(ContactsDaoImpl.class);
        bind(InformationDao.class).to(InformationDaoImpl.class);

        // binding dao layer objects

        bind(DeliveryService.class).to(DeliveryServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(StoreService.class).to(StoreServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(SaleService.class).to(SaleServiceImpl.class);
        bind(InformationService.class).to(InformationServiceImpl.class);
        bind(UserRoleService.class).to(UserRoleServiceImpl.class);
        bind(UserStoreService.class).to(UserStoreServiceImpl.class);
        bind(ContactService.class).to(ContactServiceImpl.class);
        bind(RoleService.class).to(RoleServiceImpl.class);
    }
}
