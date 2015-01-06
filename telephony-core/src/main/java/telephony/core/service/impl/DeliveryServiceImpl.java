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
import telephony.core.service.converter.DeliveryConverter;
import telephony.core.service.dto.DeliveryDto;
import telephony.core.service.dto.ProductDto;
import telephony.core.service.dto.ProductEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;


/**
 * Deliveries management service.
 */
public class DeliveryServiceImpl
extends AbstractBasicService<Delivery> 
implements DeliveryService {
	
	final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	@Inject
	PricingsDao pricingsDao;
	
    @Inject
    DeliveriesDao deliveriesDao;
    
    @Inject
    SessionService sessionService;
    
    @Inject
    StoresDao storesDao;
    
    @Inject
    ContactsDao contactsDao;

	@Inject
	ProductsDao productsDao;
	
	@Inject
	ProductTaxDao productTaxDao;
	
	@Inject
	ModelDao modelsDao;
	
	@Inject
	ProducerDao producerDao;
	
	@Inject
	TaxDao taxDao;

	@Inject
	DeliveryConverter deliveryConverter;
	
	Logger logger = LoggerFactory.getLogger(getClass());
    
    @Override
    @Transactional
    public DeliveriesFetchResponse findDeliveries(DeliveriesFetchRequest request)
    		throws SessionServiceException, DeliveryServiceException{
        
        logger.debug("DeliveryServiceImpl.findDeliveries starts");        
		logger.debug("params : [request : {}]", request);

		SessionDto session = SessionDto.create();
		session.setSessionId(request.getSessionId());
		session.setUsername(request.getUsername());
			
		sessionService.validate(session);
		
        List<Delivery> res = deliveriesDao.find(request.getFilters());
        
        DeliveriesFetchResponse resp = new DeliveriesFetchResponse();
        
        List<DeliveryDto> coll = new ArrayList<DeliveryDto>();
        
        for(Delivery d : res) {
        	coll.add(deliveryConverter.toDeliveryDto(d));
        }
        
        resp.setDeliveries(coll);
        
        return resp;
    }
    
    @Override
	@Transactional
	public long count(SessionDto session) throws SessionServiceException {

		sessionService.validate(session);

		return deliveriesDao.count();
	}

	@Transactional
	@Override
	public DeliveryAddResponse add(DeliveryAddRequest request)
			throws SessionServiceException, DeliveryServiceException, ParseException {
		
		// TODO : add validation
		// TODO : add validator
		// TODO : add bean to entity converter
		// TODO : add entity to bean converter
		
		SessionDto session = SessionDto.create();
		session.setSessionId(request.getSessionId());
		session.setUsername(request.getUsername());
		
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
		
		for (ProductDto productBean : request.getProducts()) {
			
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
	private Product toProduct(ProductDto bean, Delivery delivery, Store store, Model model) 
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
	public DeliveryDetailsResponse fetchDetails(DeliveryDetailsRequest request)
			throws SessionServiceException {

		sessionService.validate(request.getSessionDto());

		Delivery delivery = deliveriesDao.findDetailsById(request.getDeliveryId());
		DeliveryDto bean = deliveryConverter.toDeliveryDto(delivery);
		DeliveryDetailsResponse resp = new DeliveryDetailsResponse();
		resp.setDelivery(bean);
		
		return resp;
	}

	@Override
	@Transactional
	public DeliveryEditResponse edit(DeliveryEditRequest request)
			throws ParseException, DeliveryServiceException, SessionServiceException {
	
		DeliveryEditResponse resp = new DeliveryEditResponse();
		sessionService.validate(request.getSessionDto());

		// TODO : extract the stuff below to converter/methods
		
		Delivery delivery = deliveriesDao.findById(request.getId());
		Store store = null;
		
		if (request.getStoreId() != null) {
			store = storesDao.findById(request.getStoreId());
		} else {
			store = delivery.getStore();
		}
		
		delivery.setStore(store);
		
		Contact contact = null;
		if (request.getContactId() != null) {
			contact = contactsDao.findById(request.getContactId());
		} else {
			contact = delivery.getContact();
		}
		
		delivery.setContact(contact);
		
		for (Long id : request.getProductsToDelete()) { // TODO : to improve
			productsDao.removeById(id);
		}
		
		Collection<Product> products = new ArrayList<Product>();
		
		for (ProductDto bean:  request.getProductsToAdd()) {
			
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
		
		for (ProductEditDto bean : request.getProductsToEdit()) {
			
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
			} else if (model != null && producer != null && !producer.equals(model.getProducer())) {
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

	@Transactional
	@Override
	public DeliveryDeleteResponse delete(DeliveryDeleteRequest request) throws SessionServiceException, DeliveryServiceException {

		DeliveryDeleteResponse resp = new DeliveryDeleteResponse();

		logger.info("DeliveryServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ deliveryId : {} ]", request.getDeliveryId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		if (isEmpty(request.getDeliveryId())) {
			errors.add(Error.create("deliveryId", "deliveryId cannot be empty"));
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("error occured"); // TODO add lcoalized msg
			return resp;
		}

		deliveriesDao.removeById(request.getDeliveryId());
		
		resp.setSuccess(true);
		
		return resp;
	}
}
