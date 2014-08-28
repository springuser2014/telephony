package telephony.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.ModelDao;
import telephony.core.dao.ProducerDao;
import telephony.core.dao.ProductTaxDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.StoresDao;
import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Pricing;
import telephony.core.entity.jpa.Producer;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductTax;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.Tax;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.AddDeliveryRequest;
import telephony.core.service.dto.AddDeliveryResponse;
import telephony.core.service.dto.ProductBean;
import telephony.core.service.dto.ProductTaxBean;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Deliveries management service.
 */
public class DeliveryServiceImpl
extends AbstractBasicService<Delivery> 
implements DeliveryService {
	
	private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Inject
    private DeliveriesDao deliveriesDao;
    
    @Inject
    private SessionService sessionService;
    
    @Inject
    private StoresDao storesDao;
    
    @Inject
    private ContactsDao contactsDao;

	@Inject
	private ProductsDao productsDao;
	
	@Inject
	private ProductTaxDao productTaxDao;
	
	@Inject
	private ModelDao modelsDao;
	
	@Inject
	private ProducerDao producerDao;
	
	@Inject
	private TaxDao taxDao;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    @Override
    public void add(Session session, Delivery newDelivery, 
    		List<Product> products, Long storeId, Long contactId)
    		throws SessionServiceException, DeliveryServiceException {
    	
        logger.debug("DeliveryServiceImpl.addNewDelivery starts");        
		logger.debug("params : [session : {}, newDelivery : {}]", session, newDelivery);

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

    @Override
    @Transactional
    public List<Delivery> find(Session session, DeliveryFilterCriteria filters)
    		throws SessionServiceException, DeliveryServiceException{
        
        logger.debug("DeliveryServiceImpl.fetchAllDeliveries starts");        
		logger.debug("params : [session : {}, filters : {}]");

		sessionService.validate(session);
		
        List<Delivery> res = deliveriesDao.find();

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }
    
    @Override
	@Transactional
	public long count(Session session) {
		return deliveriesDao.count();
	}
    
	@Transactional
	@Override
	public void update(Session session, Delivery deliveryToUpdate) 
			throws SessionServiceException,
			DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.updateDelivery starts");        
		logger.debug("params : [session : {}, delivery: {} ]", session, deliveryToUpdate);

		sessionService.validate(session);
		
		deliveriesDao.saveOrUpdate(deliveryToUpdate);
	}

	@Transactional
	@Override
	public void delete(Session session, Delivery delvieryToDelete) 
		throws SessionServiceException, DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.delete starts");        
		logger.debug("params : [ session : {}, delivery : {} ]", session, delvieryToDelete);

		sessionService.validate(session);
		
		deliveriesDao.remove(delvieryToDelete);			
	}

	@Transactional
	@Override
	public Delivery findById(Session session, Long deliveryId) 
			throws SessionServiceException, DeliveryServiceException {
		
		logger.debug("DeliveryServiceImpl.findById starts");        
		logger.debug("params : [ session : {}, deliveryId : {} ]", session, deliveryId);

		sessionService.validate(session);
		
		Delivery delviery = deliveriesDao.findById(deliveryId);
		
		return delviery;
	}

	@Transactional
	@Override
	public AddDeliveryResponse add(AddDeliveryRequest request) 
			throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// TODO : add validation
		// TODO : add validator
		// TODO : add bean to entity converter
		// TODO : add entity to bean converter
		
		Session session = Session.create()
				.sessionId(request.getSessionId())
				.username(request.getUsername());
		
		sessionService.validate(session);		

		Delivery delivery = new Delivery();
		Store store = storesDao.findById(request.getStoreId());
		Contact contact = contactsDao.findById(request.getContactId());
		
		
		
		delivery.setDateIn(SDF.parse(request.getDateIn()));
		delivery.setLabel(request.getLabel());
		delivery.setStore(store);
		delivery.setContact(contact);
		
		delivery = deliveriesDao.saveOrUpdate(delivery);
		
		Collection<Product> products = new ArrayList<Product>();
		
		for (ProductBean productBean : request.getProducts()) {
			
			Model model = null;
			Producer producer = null;
			
			model = modelsDao.findByLabel(productBean.getModel());
			producer = producerDao.findByLabel(productBean.getProducer());
			
			Product product = toProduct(productBean, delivery, store, model);
			
			// TODO : refactor this part
			if (model != null && producer != null && model.getProducer().equals(producer)) {
				product.setModel(model);
			} else if (model != null && producer != null && !model.getProducer().equals(producer)) {
				throw new DeliveryServiceException();
			} else if (model == null && producer != null) {
				model = new Model();
				model.setLabel(productBean.getModel());
				model.setProducer(producer);
				
				model = modelsDao.saveOrUpdate(model);
			} else if (model != null && producer == null) {
				throw new DeliveryServiceException();
			} else {
				producer = new Producer();
				producer.setLabel(productBean.getProducer());
				producer = producerDao.saveOrUpdate(producer);
				
				model = new Model();
				model.setLabel(productBean.getModel());
				model.setProducer(producer);				
				model = modelsDao.saveOrUpdate(model);
			}
			
			product.setModel(model);
			productsDao.save(product);
			product = productsDao.findById(product.getId());
			
			Tax tax = taxDao.findById(productBean.getTaxId());
			
			ProductTax productTax = new ProductTax();
			productTax.setProduct(product);
			productTax.setTax(tax);
			productTax.setFrom(SDF.parse(productBean.getTaxFrom()));
			productTax.setTo(SDF.parse(productBean.getTaxTo()));
			
			productTaxDao.save(productTax);
			
			
			if (product != null ) {
				products.add(product);
			}
		}
		
		AddDeliveryResponse resp = new AddDeliveryResponse();
		resp.setSuccess(true);
		
		return resp;
	}

	private Product toProduct(ProductBean bean, Delivery delivery, Store store, Model model) 
			throws ParseException {

		Product p = new Product();
		p.setColor(bean.getColor());
		p.setImei(bean.getImei());
		p.setPriceIn(bean.getPriceIn());
		p.setStore(store);
		p.setModel(model);
		p.setDelivery(delivery);
		
		Pricing price = new Pricing();
		price.setFrom(SDF.parse(bean.getPriceFrom()));
		price.setTo(SDF.parse(bean.getPriceTo()));
		price.setRate(bean.getPriceIn());
		
		Collection<Pricing> pricings = new ArrayList<Pricing>();
		pricings.add(price);
		
		p.setPricings(pricings);
		
		return p;
	}
}
