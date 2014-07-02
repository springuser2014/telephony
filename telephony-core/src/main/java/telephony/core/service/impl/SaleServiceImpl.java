package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.SalesDao;
import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.SaleService;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Sales management services.
 */
public class SaleServiceImpl extends AbstractBasicService<Sale> implements
		SaleService {

	@Inject
	private SalesDao salesDao;

	@Inject
	private ProductsDao productsDao;
	
	@Inject 
	private ContactsDao contactsDao;
	
	@Inject
	private StoresDao storesDao;
	
	@Inject
	private SessionService sessionService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}  
	 */
	@Override
	@Transactional
	public List<Sale> findAllSales(String username, String sessionId) 
			throws SessionServiceException {
		 
		logger.debug("SaleServiceImpl.findAllSales starts");        
		logger.debug("params : [username : {}, sessionId : {}]", 
				new Object[] {username, sessionId});

		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		List<Sale> res = salesDao.find();

		logger.debug("SaleServiceImpl.findAllSales starts");

		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public long count() {
		return salesDao.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(String username, String sessionId, Sale saleToCancel)
			throws SessionServiceException, SaleServiceException {
		

		logger.debug("SaleServiceImpl.delete starts");        
		logger.debug("params : [username : {}, sessionId : {}, saleToCancel : {}]", 
				new Object[] {username, sessionId, saleToCancel});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		salesDao.remove(saleToCancel);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void updateSale(String username, String sessionId, Sale saleToUpdate)
			throws SessionServiceException, SaleServiceException {
		
		logger.debug("SaleServiceImpl.updateSale starts");        
		logger.debug("params : [username : {}, sessionId : {}, saleToUpdate: {}]", 
				new Object[] {username, sessionId, saleToUpdate});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		salesDao.saveOrUpdate(saleToUpdate);		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Sale findById(String username, String sessionId, Long saleId)
			throws SessionServiceException, SaleServiceException {
	
		logger.debug("SaleServiceImpl.findById starts");        
		logger.debug("params : [username : {}, sessionId : {}, saleId : {} ]", 
				new Object[] {username, sessionId, saleId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		Sale sale = salesDao.findById(saleId);
		
		return sale;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void addNewSale(String username, String sessionId, Sale sale,
			List<Product> products, Long storeId, Long contactId)
			throws SessionServiceException, SaleServiceException {
		
		logger.debug("SaleServiceImpl.addNewSale starts");        
		logger.debug("params : [username : {}, sessionId : {}, newSale: {}, products : {}, storeId : {}, contactId : {}]", 
				new Object[] {username, sessionId, sale, products, storeId, contactId});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		Contact contact = contactsDao.findById(contactId);
		
		Store store = storesDao.findById(storeId);
		
		sale.setContact(contact);
		sale.setStore(store);		
		salesDao.save(sale);
		
		salesDao.getEntityManager().flush();
		salesDao.getEntityManager().refresh(sale);
		
		for (Product product : products) {
			product.setStore(store);
			sale.addProduct(product);
			product.setSale(sale);
			productsDao.save(product);
		}
		
        logger.debug("SaleServiceImpl.addNewSale ends");	
	}

	@Override
	@Transactional
	public Sale findByLabel(String username, String sessionId, String label) 
			throws SessionServiceException {
		
		logger.debug("SaleServiceImpl.addNewSale starts");        
		logger.debug("params : [username : {}, sessionId : {}, label: {}]", 
				new Object[] {username, sessionId, label});

		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		return salesDao.findByLabel(label);
	}
	
	

}
