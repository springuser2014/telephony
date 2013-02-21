package telephony.core.guice;

import telephony.core.dao.implementations.ContactsDaoImpl;
import telephony.core.dao.implementations.DeliveriesDaoImpl;
import telephony.core.dao.implementations.InformationDaoImpl;
import telephony.core.dao.implementations.ProductsDaoImpl;
import telephony.core.dao.implementations.RolesDaoImpl;
import telephony.core.dao.implementations.SalesDaoImpl;
import telephony.core.dao.implementations.StoresDaoImpl;
import telephony.core.dao.implementations.SystemLogsDaoImpl;
import telephony.core.dao.implementations.UserRolesDaoImpl;
import telephony.core.dao.implementations.UserStoresDaoImpl;
import telephony.core.dao.implementations.UsersDaoImpl;
import telephony.core.dao.interfaces.ContactsDao;
import telephony.core.dao.interfaces.DeliveriesDao;
import telephony.core.dao.interfaces.InformationDao;
import telephony.core.dao.interfaces.ProductsDao;
import telephony.core.dao.interfaces.RolesDao;
import telephony.core.dao.interfaces.SalesDao;
import telephony.core.dao.interfaces.StoresDao;
import telephony.core.dao.interfaces.SystemLogsDao;
import telephony.core.dao.interfaces.UserRolesDao;
import telephony.core.dao.interfaces.UserStoresDao;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.service.impl.ContactServiceImpl;
import telephony.core.service.impl.DeliveryServiceImpl;
import telephony.core.service.impl.InformationServiceImpl;
import telephony.core.service.impl.ProductServiceImpl;
import telephony.core.service.impl.RoleServiceImpl;
import telephony.core.service.impl.SaleServiceImpl;
import telephony.core.service.impl.SessionServiceImpl;
import telephony.core.service.impl.StoreServiceImpl;
import telephony.core.service.impl.UserRoleServiceImpl;
import telephony.core.service.impl.UserServiceImpl;
import telephony.core.service.impl.UserStoreServiceImpl;
import telephony.core.service.interfaces.ContactService;
import telephony.core.service.interfaces.DeliveryService;
import telephony.core.service.interfaces.InformationService;
import telephony.core.service.interfaces.ProductService;
import telephony.core.service.interfaces.RoleService;
import telephony.core.service.interfaces.SaleService;
import telephony.core.service.interfaces.SessionService;
import telephony.core.service.interfaces.StoreService;
import telephony.core.service.interfaces.UserRoleService;
import telephony.core.service.interfaces.UserService;
import telephony.core.service.interfaces.UserStoreService;
import telephony.core.util.StringGenerator;
import telephony.core.util.StringGeneratorImpl;

import com.google.inject.AbstractModule;

/**
 * Provides some default bindings for DAOs and services.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 * @since 0.1
 */
public class TelephonyCoreServicesModule extends AbstractModule {

    /**
     *
     */
    @Override
    protected final void configure() {

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

        // binding service layer objects

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
        bind(SessionService.class).to(SessionServiceImpl.class);

        // util classses

        bind(StringGenerator.class).to(StringGeneratorImpl.class);

    }
}
