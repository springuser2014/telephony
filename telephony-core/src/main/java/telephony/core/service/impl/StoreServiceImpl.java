package telephony.core.service.impl;

import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreDeleteResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.service.exception.StoreServiceException;


/**
 * Stores management service.
 */
public class StoreServiceImpl 
extends AbstractBasicService<Store> 
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

	@Override
	public StoreFetchResponse fetch(StoreFetchRequest request) throws SessionServiceException, StoreServiceException {
		return null;
	}

	@Override
	public StoreAddResponse add(StoreAddRequest request) throws SessionServiceException, StoreServiceException {
		return null;
	}

	@Override
	public StoreDeleteResponse delete(StoreDeleteRequest request) throws SessionServiceException, StoreServiceException {
		return null;
	}

	@Override
	public StoreEditResponse edit(StoreEditRequest request) throws SessionServiceException, StoreServiceException {
		return null;
	}

	@Override
    @Transactional
    public List<Store> find(SessionDto session, StoreFilterCriteria filters) 
    		throws SessionServiceException {

        logger.info("StoreServiceImpl.fetchAllStores starts");
		logger.info("params : [session : {} , filters : {} ]", session, filters);
        
        sessionService.validate(session);

        // TODO : use fitlers
        List<Store> stores = storesDao.find(filters);

        logger.debug("found {} elements ", stores.size());

        return stores;
    }

    @Override
	@Transactional
	public long count(SessionDto session) {
		
		return storesDao.count();
	}

	@Override
	@Transactional
	public void add(SessionDto session, Store store) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.add starts");
		logger.debug("params : [ session : {}, store : {}]", session, store);

        sessionService.validate(session);

		storesDao.save(store);
	}

	@Override
	@Transactional
	public Store findByLabel(SessionDto session, String storelabel) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.findByLabel starts");
		logger.debug("params : [ session : {} , store : {} ]", session, storelabel);

		sessionService.validate(session);
		
		Store store = storesDao.findByLabel(storelabel);
		
		return store;
	}

	@Transactional
	@Override
	public void update(SessionDto session, Store storeToEdit) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.edit starts");
		logger.debug("params : [ session : {}, storeToEdit : {}]", session, storeToEdit);
		
		sessionService.validate(session);
		
		storesDao.saveOrUpdate(storeToEdit);
	}

	@Transactional
	@Override
	public void remove(SessionDto session, Store storeToDelete) 
			throws SessionServiceException {
		
		logger.debug("StoreServiceImpl.delete starts");
		logger.debug("params : [ session : {}, storeToDelete : {}]", session, storeToDelete);
		
		sessionService.validate(session);
		
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
		
		storeToDelete.setUsers(new HashSet<User>());
		storeToDelete = storesDao.saveOrUpdate(storeToDelete);
		storesDao.remove(storeToDelete);
	}

	@Transactional
	@Override
	public Store findById(SessionDto session, long moveToStoreId) 
			throws SessionServiceException {

		logger.debug("findById - params : [ session : {}, storeId : {}", 
				new Object[] {session, moveToStoreId});
	
		sessionService.validate(session);
		
		return storesDao.findById(moveToStoreId);
	}
}
