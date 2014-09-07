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
import telephony.core.query.filter.SaleFilterCriteria;
import telephony.core.service.SaleService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Sales management services.
 */
public class SaleServiceImpl 
extends AbstractBasicService<Sale> 
implements SaleService {

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

	@Override
	@Transactional
	public List<Sale> find(Session session) 
			throws SessionServiceException {
		 
		logger.debug("SaleServiceImpl.findAllSales starts");        
		logger.debug("params : [ session : {}]", session);

		sessionService.validate(session);
		SaleFilterCriteria filters = SaleFilterCriteria.create();
		List<Sale> res = salesDao.find(filters);

		logger.debug("SaleServiceImpl.findAllSales starts");

		return res;
	}

	@Override
	@Transactional
	public long count(Session session) {
		return salesDao.count();
	}

	@Override
	@Transactional
	public void delete(Session session, Sale saleToCancel)
			throws SessionServiceException, SaleServiceException {
		

		logger.debug("SaleServiceImpl.delete starts");        
		logger.debug("params : [session : {}, saleToCancel : {}]", 
				new Object[] {session, saleToCancel});

		sessionService.validate(session);
		
		salesDao.remove(saleToCancel);
	}

	@Override
	@Transactional
	public void update(Session session, Sale saleToUpdate)
			throws SessionServiceException, SaleServiceException {
		
		logger.debug("SaleServiceImpl.updateSale starts");        
		logger.debug("params : [ session : {}, saleToUpdate: {}]", 
				new Object[] {session, saleToUpdate});

		sessionService.validate(session);
		
		salesDao.saveOrUpdate(saleToUpdate);		
	}

	@Override
	@Transactional
	public Sale findById(Session session, Long saleId)
			throws SessionServiceException, SaleServiceException {
	
		logger.debug("SaleServiceImpl.findById starts");        
		logger.debug("params : [ session : {}, saleId : {} ]", 
				new Object[] { session , saleId});

		sessionService.validate(session);
		
		Sale sale = salesDao.findById(saleId);
		
		return sale;
	}

	@Override
	@Transactional
	public void add(Session session, Sale sale, List<Product> products,
			Long storeId, Long contactId)
			throws SessionServiceException, SaleServiceException {
		
		logger.debug("SaleServiceImpl.addNewSale starts");        
		logger.debug("params : [session : {}, newSale: {}, products : {}, storeId : {}, contactId : {}]", 
				new Object[] {session, sale, products, storeId, contactId});

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
	public Sale findByLabel(Session session, String label) 
			throws SessionServiceException {
		
		logger.debug("SaleServiceImpl.addNewSale starts");        
		logger.debug("params : [ session : {}, label: {}]", 
				new Object[] {session, label});

		sessionService.validate(session);
		
		return salesDao.findByLabel(label);
	}
}
