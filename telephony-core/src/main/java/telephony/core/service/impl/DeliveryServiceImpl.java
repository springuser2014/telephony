package telephony.core.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Deliveries management service.
 */
public class DeliveryServiceImpl
    extends AbstractBasicService<Delivery> implements DeliveryService {

    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private ProductsDao prodctsDao;
    
    @Inject
    private SessionService sessionService;
    
    @Inject
    private StoresDao storesDao;
    
    @Inject
    private ContactsDao contactsDao;

	@Inject
	private ProductsDao productsDao;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void addNewDelivery(String username, String sessionId, 
    		Delivery newDelivery, List<Product> products, Long storeId, Long contactId)
    		throws SessionServiceException, DeliveryServiceException {
    	
        logger.debug("DeliveryServiceImpl.addNewDelivery starts");        
		logger.debug("params : [username : {}, sessionId : {}, newDelivery : {}]", 
				new Object[] {username, sessionId, newDelivery});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		Contact contact = contactsDao.findById(contactId);
		
		Store store = storesDao.findById(storeId);
		
		newDelivery.setContact(contact);
		newDelivery.setStore(store);
		
		newDelivery = deliveriesDao.saveOrUpdate(newDelivery);		
		deliveriesDao.getEntityManager().flush();
		
		for (Product product : products) {
			
			product.setStore(store);
			newDelivery.addProduct(product);
			product.setDelivery(newDelivery);			
			
			productsDao.saveOrUpdate(product);		
		}
		
        logger.debug("DeliveryServiceImpl.addNewDelivery ends");
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<Delivery> fetchAllDeliveries(String username, String sessionId)
    		throws SessionServiceException, DeliveryServiceException{
        
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");        
		logger.debug("params : [username : {}, sessionId : {}]", 
				new Object[] {username, sessionId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
        List<Delivery> res = deliveriesDao.find();

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
	@Transactional
	public long count() {
		return deliveriesDao.count();
	}
    
	/**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public void updateDelivery(String username, String sessionId, Delivery delvieryToUpdate) 
			throws SessionServiceException,
			DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.updateDelivery starts");        
		logger.debug("params : [username : {}, sessionId : {}]", 
				new Object[] {username, sessionId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		deliveriesDao.saveOrUpdate(delvieryToUpdate);
	}

    /**
     * {@inheritDoc}
     */
	@Transactional
	@Override
	public void delete(String username, String sessionId, Delivery delvieryToDelete) 
		throws SessionServiceException, DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.delete starts");        
		logger.debug("params : [username : {}, sessionId : {}]", 
				new Object[] {username, sessionId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		deliveriesDao.remove(delvieryToDelete);			
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public Delivery findById(String username, String sessionId, Long deliveryId) 
			throws SessionServiceException, DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.findById starts");        
		logger.debug("params : [username : {}, sessionId : {}, deliveryId : {} ]", 
				new Object[] {username, sessionId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		Delivery delviery = deliveriesDao.findById(deliveryId);
		
		return delviery;
	}
}
