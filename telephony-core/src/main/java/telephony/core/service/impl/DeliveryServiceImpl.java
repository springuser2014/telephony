package telephony.core.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.DeliveryConverter;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;


/**
 * Deliveries management service.
 */
public class DeliveryServiceImpl
extends AbstractBasicService<Delivery> 
implements DeliveryService {

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
    		throws SessionServiceException, DeliveryServiceException {
        
        logger.info("DeliveryServiceImpl.findDeliveries starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {}]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());
		
        List<Delivery> res = deliveriesDao.find(request.getFilters());
        
        DeliveriesFetchResponse resp = new DeliveriesFetchResponse();
        
        List<DeliveryDto> coll = new ArrayList<DeliveryDto>();
        
        for(Delivery d : res) {
        	coll.add(deliveryConverter.toDeliveryDto(d));
        }
        
        resp.setDeliveries(coll);
		resp.setMessage("operation peformed successfuly"); // TODO add localized msg
        
        return resp;
    }
    
    @Override
	@Transactional
	public long count(SessionDto session)
			throws SessionServiceException {

		sessionService.validate(session);

		return deliveriesDao.count();
	}

	@Transactional
	@Override
	public DeliveryAddResponse add(DeliveryAddRequest request)
			throws SessionServiceException, DeliveryServiceException {

		DeliveryAddResponse resp = new DeliveryAddResponse();

		logger.info("DeliveryServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ deliveryDto : {} ] ", request.getDeliveryDto());
		}

		sessionService.validate(request.getSessionDto());

		DeliveryAddDto addDto = request.getDeliveryDto();

		List<Error> errors = getEmptyErrors();

		if (!validate(addDto, errors)) {

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError");
			return resp;
		}

		Delivery delivery = deliveryConverter.toEntity(addDto);
		deliveriesDao.saveOrUpdate(delivery);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(DeliveryAddDto addDto, List<Error> errors) {

		if (isNull(addDto)) {
			errors.add(Error.create("deliveryDto", "deliveryDto cannot be null"));
			return false;
		}

		if (isEmpty(addDto.getContactId())) {
			errors.add(Error.create("contactId", "contactId cannot be empty"));
		}

		if (isEmpty(addDto.getStoreId())) {
			errors.add(Error.create("storeId", "storeId cannot be empty"));
		}

		if (isEmpty(addDto.getLabel())) {
			errors.add(Error.create("label", "label cannot be empty"));
		}

		if (isNull(addDto.getDateIn())) {
			errors.add(Error.create("dateIn", "dateIn cannot be null"));
		}

		if (isNull(addDto.getProducts())) {
			errors.add(Error.create("products", "products cannot be null"));
		}

		if (isEmpty(addDto.getProducts())) {
			errors.add(Error.create("products", "products cannot be empty"));
		}

		int i = 0;

		for (ProductAddDto dto : addDto.getProducts()) {

			String prod = "products[" + i + "]";

			if (isEmpty(dto.getColor())) {
				errors.add(Error.create(prod + ".color", "color cannot be empty"));
			}

			if (isEmpty(dto.getImei())) {
				errors.add(Error.create(prod + ".imei", "color cannot be empty"));
			}

			if (isNotEmpty(dto.getImei()) && dto.getImei().length() != 15) {
				errors.add(Error.create(prod + ".imei", "imei should be 15 character long"));
			}

			if (isNull(dto.getCurrentPrice())) {
				errors.add(Error.create(prod + ".currentPrice", "currentPrice cannot be empty"));
			} else {

				if (isEmpty(dto.getCurrentPrice().getRate())) {
					errors.add(Error.create(prod + ".currentPrice.rate", "currentPrice.rate cannot be empty"));
				}

				if (isNull(dto.getCurrentPrice().getFrom())) {
					errors.add(Error.create(prod + ".currentPrice.from", "currentPrice.from cannot be empty"));
				}

				if (isNull(dto.getCurrentPrice().getTo())) {
					errors.add(Error.create(prod + ".currentPrice.to", "currentPrice.to cannot be empty"));
				}
			}

			if (isEmpty(dto.getModel())) {
				errors.add(Error.create(prod + ".model", "model cannot be empty"));
			}

			if (isEmpty(dto.getProducer())) {
				errors.add(Error.create(prod + ".producer", "producer cannot be empty"));
			}

			if (isNull(dto.getProductTax())) {
				errors.add(Error.create(prod + ".productTax", "productTax cannot be null"));
			} else {
				if (isEmpty(dto.getProductTax().getId())) {
					errors.add(Error.create(prod + ".productTax.id", "productTax.id cannot be empty"));
				}

				if (isNull(dto.getProductTax().getTaxFrom())) {
					errors.add(Error.create(prod + ".productTax.taxFrom", "productTax.taxFrom cannot be empty"));
				}

				if (isNull(dto.getProductTax().getTaxTo())) {
					errors.add(Error.create(prod + ".productTax.taxFrom", "taxId cannot be empty"));
				}
			}

			i++;
		}

		return errors.size() == 0;
	}

	// TODO : extract to converter
	private Product toProduct(ProductAddDto bean, Delivery delivery, Store store, Model model)
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

		DeliveryEditDto editDto = request.getDeliveryDto() ;

		// TODO : extract the stuff below to converter/methods
		
		Delivery delivery = deliveriesDao.findById(editDto.getId());
		deliveryConverter.updateEntity(delivery, request.getDeliveryDto());
		deliveriesDao.saveOrUpdate(delivery);

		Store store = null;

		if (editDto.getStoreId() != null) {
			store = storesDao.findById(editDto.getStoreId());
		} else {
			store = delivery.getStore();
		}

		delivery.setStore(store);

		Contact contact = null;
		if (editDto.getContactId() != null) {
			contact = contactsDao.findById(editDto.getContactId());
		} else {
			contact = delivery.getContact();
		}

		delivery.setContact(contact);

		for (Long id : editDto.getProductsToDelete()) { // TODO : to improve
			productsDao.removeById(id);
		}

		Collection<Product> products = new ArrayList<Product>();

		for (ProductAddDto dto : editDto.getProductsToAdd()) {

			Model model = null;
			Producer producer = null;

			model = modelsDao.findByLabel(dto.getModel());
			producer = producerDao.findByLabel(dto.getProducer());

			Product product = toProduct(dto, delivery, store, model);

			if (model != null && producer != null && model.getProducer().equals(producer)) {
				product.setModel(model);
			} else if (model != null && producer != null && !model.getProducer().equals(producer)) {
				throw new DeliveryServiceException();
			} else if (model == null && producer != null) {
				model = new Model();
				model.setLabel(dto.getModel());
				model.setProducer(producer);

				model = modelsDao.saveOrUpdate(model);
			} else if (model != null && producer == null) {
				throw new DeliveryServiceException();
			} else {
				producer = new Producer();
				producer.setLabel(dto.getProducer());
				producer = producerDao.saveOrUpdate(producer);

				model = new Model();
				model.setLabel(dto.getModel());
				model.setProducer(producer);
				model = modelsDao.saveOrUpdate(model);
			}

			product.setModel(model);
			productsDao.save(product);
			product = productsDao.findById(product.getId());

			Pricing price = new Pricing();
			price.setFrom(dto.getCurrentPrice().getFrom());
			price.setTo(dto.getCurrentPrice().getTo());
			price.setRate(dto.getCurrentPrice().getRate());

			product.setPriceIn(dto.getPriceIn());

			if (product.getPricings() == null) {
				product.setPricings(new ArrayList<Pricing>());
			}
			price.setProduct(product);
			pricingsDao.save(price);

			Tax tax = taxDao.findById(dto.getProductTax().getId());

			ProductTax productTax = new ProductTax();
			productTax.setProduct(product);
			productTax.setTax(tax);
			productTax.setFrom(dto.getProductTax().getTaxFrom());
			productTax.setTo(dto.getProductTax().getTaxTo());

			productTaxDao.save(productTax);

			if (product != null ) {
				products.add(product);
			}
		}

		for (ProductEditDto bean : editDto.getProductsToEdit()) {

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

//			productsDao.saveOrUpdate(product);

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
		resp.setMessage("operation performed successfully");
		
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
		resp.setMessage("operation performed successfully");
		
		return resp;
	}
}
