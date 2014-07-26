package telephony.core.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.RolesDao;
import telephony.core.dao.SalesDao;
import telephony.core.dao.StoresDao;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Stores management service.
 */
public class StoreServiceImpl extends AbstractBasicService<Store> 
	implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @Inject
    private ProductsDao productsDao;

    @Inject
    private DeliveriesDao deliveriesDao;
    
    @Inject
    private StoresDao storesDao;
    
    @Inject
    private SessionService sessionService;

    @Inject
	private SalesDao salesDao;
 

    @Inject 
    private UsersDao usersDao;
    
    @Inject
    private RolesDao rolesDao;
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Store> fetchAllStores(String username, String sessionId) 
    		throws SessionServiceException {

        logger.debug("StoreServiceImpl.fetchAllStores starts");
        
        Session sessionToValidate = Session.create(username, sessionId);
        sessionService.validate(sessionToValidate);

        List<Store> stores = storesDao.find();

        logger.debug("found {} elements ", stores.size());

        return stores;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public long count() {
		
		return storesDao.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void add(String username, String sessionId, Store store) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.add starts");
		logger.debug("params : [username : {}, sessionId : {}, store : {}]", 
				username, sessionId, store);

        Session sessionToValidate = Session.create(username, sessionId);
        sessionService.validate(sessionToValidate);

		storesDao.save(store);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Store findByLabel(String username, String sessionId, String storelabel) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.findByLabel starts");
		logger.debug("params : [username : {}, sessionId : {}, storelabel : {}]", 
				username, sessionId, storelabel);

		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		Store store = storesDao.findByLabel(storelabel);
		
		return store;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void edit(String username, String sessionId, Store storeToEdit) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.edit starts");
		logger.debug("params : [username : {}, sessionId : {}, storeToEdit : {}]", 
				username, sessionId, storeToEdit);
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		storesDao.saveOrUpdate(storeToEdit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void delete(String username, String sessionId, Store storeToDelete) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.delete starts");
		logger.debug("params : [username : {}, sessionId : {}, storeToDelete : {}]", 
				username, sessionId, storeToDelete);
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		List<Sale> sales = salesDao.findByStore(storeToDelete);
		
		for (Sale sale : sales) { 
			List<Product> prodSales = productsDao.findBySale(sale);
			productsDao.remove(prodSales);
		}
				
		salesDao.remove(sales);
		
		List<Product> products = productsDao.findByStore(storeToDelete);
		productsDao.remove(products);
		
		List<Delivery> deliveries = deliveriesDao.findByStore(storeToDelete);
		deliveriesDao.remove(deliveries);
		
		storeToDelete.setRequiredRoles(new HashSet<Role>());
		storeToDelete.setUsers(new HashSet<User>());
		storesDao.save(storeToDelete);
		storesDao.remove(storeToDelete);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void setRequiredRoles(String username, String sessionId, Store store, List<Role> roles)
			throws SessionServiceException, RoleServiceException {
		
		logger.debug(
		"setRequiredRoles - params : [ username : {}, sessionId : {}, store : {}, roles : {}]", 
				new Object[] {username, sessionId, store, roles});
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
				
		Set<Role> rolesSet = new HashSet<Role>();
		rolesSet.addAll(roles);
		store.setRequiredRoles(rolesSet);
		storesDao.saveOrUpdate(store);
		storesDao.getEntityManager().flush();
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Role> getRequestRoles(String username, String sessionId, Store store) 
			throws SessionServiceException {
		logger.debug("getRequestRoles - params : [username : {} , sessionId : {}, store : {}", 
				new Object[] {username, sessionId, store});
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return rolesDao.findStoreRequiredRoles(store);
	}
	/**
	 * {@inheritDoc} 
	 */
	@Transactional
	@Override
	public Store findById(String username, String sessionId, long moveToStoreId) 
			throws SessionServiceException {

		logger.debug("findById - params : [username : {} , sessionId : {}, storeId : {}", 
				new Object[] {username, sessionId, moveToStoreId});
	
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return storesDao.findById(moveToStoreId);
	}

	
}
