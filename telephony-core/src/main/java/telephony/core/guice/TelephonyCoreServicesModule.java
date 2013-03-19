package telephony.core.guice;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import telephony.core.dao.ContactsDao;
import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.InformationDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.RolesDao;
import telephony.core.dao.SalesDao;
import telephony.core.dao.StoresDao;
import telephony.core.dao.SystemLogsDao;
import telephony.core.dao.UserRolesDao;
import telephony.core.dao.UserStoresDao;
import telephony.core.dao.UsersDao;
import telephony.core.dao.impl.ContactsDaoImpl;
import telephony.core.dao.impl.DeliveriesDaoImpl;
import telephony.core.dao.impl.InformationDaoImpl;
import telephony.core.dao.impl.ProductsDaoImpl;
import telephony.core.dao.impl.RolesDaoImpl;
import telephony.core.dao.impl.SalesDaoImpl;
import telephony.core.dao.impl.StoresDaoImpl;
import telephony.core.dao.impl.SystemLogsDaoImpl;
import telephony.core.dao.impl.UserRolesDaoImpl;
import telephony.core.dao.impl.UserStoresDaoImpl;
import telephony.core.dao.impl.UsersDaoImpl;
import telephony.core.service.ContactService;
import telephony.core.service.DeliveryService;
import telephony.core.service.InformationService;
import telephony.core.service.ProductService;
import telephony.core.service.RoleService;
import telephony.core.service.SaleService;
import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.core.service.UserRoleService;
import telephony.core.service.UserService;
import telephony.core.service.UserStoreService;
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
import telephony.core.util.StringGenerator;
import telephony.core.util.StringGeneratorImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.googlecode.flyway.core.Flyway;

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
    
    /**
     * asd.
     * @return asd.
     */
    @Provides
    @Singleton
    public Flyway migratorProvider() {
    	Flyway m = new Flyway();
    	
    	
    	// TODO : fetching connection params from persistence.xml or properties
    	DataSource dataSource = new SimpleDriverDataSource(
    			new org.postgresql.Driver(), 
    			"jdbc:postgresql://localhost:5432/telephony", 
    			"postgres",
    			"postgres"
    	);
    	
    	
    	m.setDataSource(dataSource);
    	m.migrate();
    	
    	return m;    	
    }
}
