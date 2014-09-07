package telephony.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.*;
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
	private PricingsDao pricingsDao;
	
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
		
        List<Delivery> res = deliveriesDao.find(filters);

        logger.debug("DeliveryServiceImpl.fetchAllDeliveries ends");

        return res;
    }
    
    @Override
    @Transactional
    public DeliveriesFetchResponse findDeliveries(DeliveriesFetchRequest request)
    		throws SessionServiceException, DeliveryServiceException{
        
        logger.debug("DeliveryServiceImpl.findDeliveries starts");        
		logger.debug("params : [request : {}]", request);

		Session session = Session.create()
				.sessionId(request.getSessionId())
				.username(request.getUsername());
			
		sessionService.validate(session);
		
        List<Delivery> res = deliveriesDao.find(request.getFilters());
        
        DeliveriesFetchResponse resp = new DeliveriesFetchResponse();
        
        List<DeliveryBean> coll = new ArrayList<DeliveryBean>();
        
        for(Delivery d : res) {
        	coll.add(toDelivery(d));
        }
        
        resp.setDeliveries(coll);
        
        return resp;
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
	public DeliveryAddResponse add(DeliveryAddRequest request) 
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
		
		DeliveryAddResponse resp = new DeliveryAddResponse();
		resp.setSuccess(true);
		
		return resp;
	}

	// TODO : extract to converter
	private Product toProduct(ProductBean bean, Delivery delivery, Store store, Model model) 
			throws ParseException {

		Product p = new Product();
		p.setColor(bean.getColor());
		p.setImei(bean.getImei());
		p.setPriceIn(bean.getPriceIn());
		p.setStore(store);
		p.setModel(model);
		p.setDelivery(delivery);
		
		return p;
	}

	@Transactional
	@Override
	public DeliveryDetailsResponse findDetails(DeliveryDetailsRequest request) 
			throws SessionServiceException {
		
		Session session = Session.create()
				.sessionId(request.getSessionId())
				.username(request.getUsername());
		
		sessionService.validate(session);		

		Delivery delivery = deliveriesDao.findDetailsById(request.getDeliveryId());
		DeliveryBean bean = toDelivery(delivery);
		DeliveryDetailsResponse resp = new DeliveryDetailsResponse();
		resp.setDelivery(bean);
		
		return resp;
	}

	
	// TODO extract to converter
	private DeliveryBean toDelivery(Delivery delivery) {
		
		DeliveryBean bean = new DeliveryBean();
		bean.setContactId(delivery.getContact().getId());
		bean.setDateIn(delivery.getDateIn());
		bean.setStoreId(delivery.getStore().getId());
		bean.setId(delivery.getId());
		bean.setLabel(delivery.getLabel());
		
		List<ProductBean> products = new ArrayList<ProductBean>();
		
		if (delivery.getProducts() == null) {
			delivery.setProducts(new ArrayList<Product>());
		}
		
		for (Product prod : delivery.getProducts()) {
			ProductBean p = toProduct(prod);
			products.add(p);
		}
		
		bean.setProducts(products);
		
		return bean;
	}

	// TODO extract to converter
	private ProductBean toProduct(Product product) {
		
		ProductBean p = new ProductBean();
		p.setColor(product.getColor());
		p.setImei(product.getImei());
		p.setModel(product.getModel().getLabel());
		p.setProducer(product.getModel().getProducer().getLabel());
		if (product.getCurrentTax() != null) {
			p.setTaxFrom(product.getCurrentTax().getFrom());
			p.setTaxTo(product.getCurrentTax().getTo());
			p.setTaxId(product.getCurrentTax().getTax().getId());
		}
		
		p.setPriceIn(product.getPriceIn());
		
		if (product.getCurrentPricing() != null) {
			p.setPriceFrom(product.getCurrentPricing().getFrom());
			p.setPriceTo(product.getCurrentPricing().getTo());
			p.setCurrentPrice(product.getCurrentPricing().getRate());
		}
		
		return p;
	}
	
	@Override
	@Transactional
	public DeliveryEditResponse edit(DeliveryEditRequest req) 
			throws ParseException, DeliveryServiceException, SessionServiceException {
	
		DeliveryEditResponse resp =
				new DeliveryEditResponse();
		

		Session session = Session.create()
				.sessionId(req.getSessionId())
				.username(req.getUsername());
		
		sessionService.validate(session);		
		
		Delivery delivery = deliveriesDao.findById(req.getId());		
		Store store = null;
		
		if (req.getStoreId() != null) {
			store = storesDao.findById(req.getStoreId());
		} else {
			store = delivery.getStore();
		}
		
		delivery.setStore(store);
		
		Contact contact = null;
		if (req.getContactId() != null) {
			contact = contactsDao.findById(req.getContactId());
		} else {
			contact = delivery.getContact();
		}
		
		delivery.setContact(contact);
		
		for (Long id : req.getProductsToDelete()) { // TODO : to improve
			productsDao.removeById(id);
		}
		
		Collection<Product> products = new ArrayList<Product>();
		
		for (ProductBean bean:  req.getProductsToAdd()) {
			
			Model model = null;
			Producer producer = null;
			
			model = modelsDao.findByLabel(bean.getModel());
			producer = producerDao.findByLabel(bean.getProducer());
			
			Product product = toProduct(bean, delivery, store, model);
			
			if (model != null && producer != null && model.getProducer().equals(producer)) {
				product.setModel(model);
			} else if (model != null && producer != null && !model.getProducer().equals(producer)) {
				throw new DeliveryServiceException();
			} else if (model == null && producer != null) {
				model = new Model();
				model.setLabel(bean.getModel());
				model.setProducer(producer);
				
				model = modelsDao.saveOrUpdate(model);
			} else if (model != null && producer == null) {
				throw new DeliveryServiceException();
			} else {
				producer = new Producer();
				producer.setLabel(bean.getProducer());
				producer = producerDao.saveOrUpdate(producer);
				
				model = new Model();
				model.setLabel(bean.getModel());
				model.setProducer(producer);				
				model = modelsDao.saveOrUpdate(model);
			}
			
			product.setModel(model);
			productsDao.save(product);
			product = productsDao.findById(product.getId());
			
			Pricing price = new Pricing();
			if (bean.getPriceFrom() != null) {
				price.setFrom(SDF.parse(bean.getPriceFrom()));
			} else {
				price.setFrom(null);
			}
			if (bean.getPriceTo() != null) {
				price.setTo(SDF.parse(bean.getPriceTo()));
			} else {
				price.setTo(null);
			}
			price.setRate(bean.getPriceIn());
			
			if (product.getPricings() == null) {
				product.setPricings(new ArrayList<Pricing>());
			}
			price.setProduct(product);
			pricingsDao.save(price);
			
			Tax tax = taxDao.findById(bean.getTaxId());
			
			ProductTax productTax = new ProductTax();
			productTax.setProduct(product);
			productTax.setTax(tax);
			
			if (bean.getTaxFrom() != null) {
				productTax.setFrom(SDF.parse(bean.getTaxFrom()));
			} else {
				productTax.setFrom(null);
			}
			if (bean.getTaxTo() != null) {
				productTax.setTo(SDF.parse(bean.getTaxTo()));	
			} else {
				productTax.setTo(null);
			}
			
			productTaxDao.save(productTax);
			
			if (product != null ) {
				products.add(product);
			}
		}
		
		for (ProductEditBean bean : req.getProductsToEdit()) {
			
			Product product = productsDao.findById(bean.getId());
			
			if (bean.getColor() != null) {
				product.setColor(bean.getColor());
			}
			if (bean.getImei() != null) {
				product.setImei(bean.getImei());
			}
			
			if (bean.getPriceIn() != null) {
				product.setPriceIn(bean.getPriceIn());
			}
			
			product.setStore(store);
			
			if (bean.getColor() != null) {
				product.setColor(bean.getColor());
			}
			
			if (bean.getImei() != null) {
				product.setImei(bean.getImei());
			}
			
			if (bean.getPriceIn() != null) {
				product.setPriceIn(bean.getPriceIn());
			}
			
			Model model = null;
			Producer producer = null;
			
			model = modelsDao.findByLabel(bean.getModel());
			producer = producerDao.findByLabel(bean.getProducer());
			
			if (model != null && producer != null && model.getProducer().equals(producer)) {
				product.setModel(model);
			} else if (model != null && producer != null && !model.getProducer().equals(producer)) {
				throw new DeliveryServiceException();
			} else if (model == null && producer != null) {
				model = new Model();
				model.setLabel(bean.getModel());
				model.setProducer(producer);
				
				model = modelsDao.saveOrUpdate(model);
			} else if (model != null && producer == null) {
				throw new DeliveryServiceException();
			} else {
				producer = new Producer();
				producer.setLabel(bean.getProducer());
				producer = producerDao.saveOrUpdate(producer);
				
				model = new Model();
				model.setLabel(bean.getModel());
				model.setProducer(producer);				
				model = modelsDao.saveOrUpdate(model);
			}
			
			product.setModel(model);
			Date d = new Date();
			
			if (product.getCurrentPricing() != null) {
				
				if (product.getCurrentPricing().getRate() != bean.getPrice()) {
					
					Pricing currPricing = product.getCurrentPricing();
					currPricing.setTo(d);
										
					Pricing price = new Pricing();
					price.setProduct(product);
					price.setRate(bean.getPrice());
					price.setFrom(d);
					price.setTo(null);
					
					product.addPricing(price);
				}
			}
			
			productsDao.saveOrUpdate(product);
			
			if (product.getCurrentTax() != null) {
				if (product.getCurrentTax().getId() != bean.getTaxId()) {
					
					ProductTax currProductTax = product.getCurrentTax();
					currProductTax.setTo(d);
					
					Tax tax = taxDao.findById(bean.getTaxId());
					
					ProductTax productTax = new ProductTax();
					productTax.setProduct(product);
					productTax.setTax(tax);
					productTax.setFrom(d);
					productTax.setTo(null);
					
					productTaxDao.save(productTax);
				}
			} 
		}
		
		deliveriesDao.saveOrUpdate(delivery);
		
		resp.setSuccess(true);
		
		return resp;
	}
}
