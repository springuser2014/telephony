package telephony.core.guice;

import telephony.core.dao.ComplaintCommentDao;
import telephony.core.dao.ContactsDao;
import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.InformationDao;
import telephony.core.dao.ModelDao;
import telephony.core.dao.PricingsDao;
import telephony.core.dao.ProducerDao;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.dao.ProductTaxDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.RolesDao;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.dao.SalesDao;
import telephony.core.dao.StoresDao;
import telephony.core.dao.TaxDao;
import telephony.core.dao.UsersDao;
import telephony.core.dao.impl.ComplaintCommentDaoImpl;
import telephony.core.dao.impl.ContactsDaoImpl;
import telephony.core.dao.impl.DeliveriesDaoImpl;
import telephony.core.dao.impl.InformationDaoImpl;
import telephony.core.dao.impl.ModelDaoImpl;
import telephony.core.dao.impl.PricingsDaoImpl;
import telephony.core.dao.impl.ProducerDaoImpl;
import telephony.core.dao.impl.ProductComplaintDaoImpl;
import telephony.core.dao.impl.ProductTaxDaoImpl;
import telephony.core.dao.impl.ProductsDaoImpl;
import telephony.core.dao.impl.RolesDaoImpl;
import telephony.core.dao.impl.SaleComplaintDaoImpl;
import telephony.core.dao.impl.SalesDaoImpl;
import telephony.core.dao.impl.StoresDaoImpl;
import telephony.core.dao.impl.TaxDaoImpl;
import telephony.core.dao.impl.UsersDaoImpl;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.ContactService;
import telephony.core.service.DeliveryService;
import telephony.core.service.InformationService;
import telephony.core.service.ModelService;
import telephony.core.service.PricingService;
import telephony.core.service.ProducerService;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.ProductService;
import telephony.core.service.RoleService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.SaleService;
import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.core.service.TaxService;
import telephony.core.service.UserService;
import telephony.core.service.impl.ComplaintCommentServiceImpl;
import telephony.core.service.impl.ContactServiceImpl;
import telephony.core.service.impl.DeliveryServiceImpl;
import telephony.core.service.impl.InformationServiceImpl;
import telephony.core.service.impl.ModelServiceImpl;
import telephony.core.service.impl.PricingServiceImpl;
import telephony.core.service.impl.ProducerServiceImpl;
import telephony.core.service.impl.ProductComplaintServiceImpl;
import telephony.core.service.impl.ProductServiceImpl;
import telephony.core.service.impl.RoleServiceImpl;
import telephony.core.service.impl.SaleComplaintServiceImpl;
import telephony.core.service.impl.SaleServiceImpl;
import telephony.core.service.impl.SessionServiceImpl;
import telephony.core.service.impl.StoreServiceImpl;
import telephony.core.service.impl.TaxServiceImpl;
import telephony.core.service.impl.UserServiceImpl;
import telephony.core.util.StringGenerator;
import telephony.core.util.StringGeneratorImpl;

import com.google.inject.AbstractModule;

/**
 * Provides some default bindings for DAOs and services.
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
        bind(ContactsDao.class).to(ContactsDaoImpl.class);
        bind(InformationDao.class).to(InformationDaoImpl.class);
        bind(ModelDao.class).to(ModelDaoImpl.class);
        bind(ProducerDao.class).to(ProducerDaoImpl.class);
        bind(TaxDao.class).to(TaxDaoImpl.class);
        bind(SaleComplaintDao.class).to(SaleComplaintDaoImpl.class);
        bind(ProductComplaintDao.class).to(ProductComplaintDaoImpl.class);
        bind(PricingsDao.class).to(PricingsDaoImpl.class);
        bind(ComplaintCommentDao.class).to(ComplaintCommentDaoImpl.class);
        bind(ProductTaxDao.class).to(ProductTaxDaoImpl.class);

        // binding service layer objects

        bind(DeliveryService.class).to(DeliveryServiceImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
        bind(StoreService.class).to(StoreServiceImpl.class);
        bind(UserService.class).to(UserServiceImpl.class);
        bind(SaleService.class).to(SaleServiceImpl.class);
        bind(InformationService.class).to(InformationServiceImpl.class);        
        bind(ContactService.class).to(ContactServiceImpl.class);
        bind(RoleService.class).to(RoleServiceImpl.class);
        bind(SessionService.class).to(SessionServiceImpl.class);
        bind(ModelService.class).to(ModelServiceImpl.class);
        bind(ProducerService.class).to(ProducerServiceImpl.class);
        bind(TaxService.class).to(TaxServiceImpl.class);
        bind(SaleComplaintService.class).to(SaleComplaintServiceImpl.class);
        bind(ProductComplaintService.class).to(ProductComplaintServiceImpl.class);
        bind(PricingService.class).to(PricingServiceImpl.class);
        bind(ComplaintCommentService.class).to(ComplaintCommentServiceImpl.class);
        
        // util classses

        bind(StringGenerator.class).to(StringGeneratorImpl.class);
    }
}
