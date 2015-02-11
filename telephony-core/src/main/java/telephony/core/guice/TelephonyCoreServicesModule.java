package telephony.core.guice;

import telephony.core.dao.*;
import telephony.core.dao.impl.*;
import telephony.core.service.*;
import telephony.core.service.converter.*;
import telephony.core.service.impl.*;
import telephony.core.util.StringGenerator;
import telephony.core.util.StringGeneratorImpl;

import com.google.inject.AbstractModule;

/**
 * Provides some default bindings for DAOs and services.
 */
public class TelephonyCoreServicesModule extends AbstractModule {

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
        bind(ProductComplaintCommentDao.class).to(ProductComplaintCommentDaoImpl.class);
        bind(SaleComplaintCommentDao.class).to(SaleComplaintCommentDaoImpl.class);
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
        bind(SessionManager.class).to(SessionManagerImpl.class);
        bind(ModelService.class).to(ModelServiceImpl.class);
        bind(ProducerService.class).to(ProducerServiceImpl.class);
        bind(TaxService.class).to(TaxServiceImpl.class);
        bind(SaleComplaintService.class).to(SaleComplaintServiceImpl.class);
        bind(ProductComplaintService.class).to(ProductComplaintServiceImpl.class);
        bind(PricingService.class).to(PricingServiceImpl.class);
        bind(ProductComplaintCommentService.class).to(ProductComplaintCommentServiceImpl.class);
        bind(SaleComplaintCommentService.class).to(SaleComplaintCommentServiceImpl.class);

        bind(PermissionChecker.class).to(PermissionCheckerImpl.class);

        // binding entity to dto converters

        bind(UserConverter.class);
        bind(DeliveryConverter.class);
        bind(SaleConverter.class);
        bind(ProductConverter.class);
        bind(ModelConverter.class);
        bind(ProducerConverter.class);
        bind(TaxConverter.class);
        bind(ContactConverter.class);
        bind(RoleConverter.class);
        bind(StoreConverter.class);
        bind(ProductComplaintConverter.class);
        bind(SaleComplaintConverter.class);
        bind(ComplaintCommentConverter.class);
        
        // util classses

        bind(StringGenerator.class).to(StringGeneratorImpl.class);
    }
}
