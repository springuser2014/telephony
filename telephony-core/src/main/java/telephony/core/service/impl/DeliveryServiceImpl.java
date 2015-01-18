package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.*;
import telephony.core.entity.jpa.Delivery;
import telephony.core.service.DeliveryService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.DeliveryConverter;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.*;

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
		resp.setSuccess(true);
        
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
				if (isEmpty(dto.getProductTax().getTaxId())) {
					errors.add(Error.create(prod + ".productTax.id", "productTax.id cannot be empty"));
				}

				if (isNull(dto.getProductTax().getFrom())) {
					errors.add(Error.create(prod + ".productTax.from", "productTax.taxFrom cannot be empty"));
				}
			}

			i++;
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public DeliveryDetailsResponse fetchDetails(DeliveryDetailsRequest request)
			throws SessionServiceException {

		logger.info("DeliveryServiceImpl.fetchDetails starts");

		DeliveryDetailsResponse resp = new DeliveryDetailsResponse();

		sessionService.validate(request.getSessionDto());

		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {

			resp.setErrors(errors);
			resp.setMessage("validationFailed");
			resp.setSuccess(false);

			return resp;
		}

		Delivery delivery = deliveriesDao.findDetailsById(request.getDeliveryId());
		DeliveryDto deliveryDto = deliveryConverter.toDeliveryDto(delivery);

		resp.setDelivery(deliveryDto);
		resp.setMessage("operation performed successfully");
		resp.setSuccess(false);

		return resp;
	}

	private boolean validate(DeliveryDetailsRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getDeliveryId())) {
			errors.add(Error.create("deliveryId", "deliveryId cannot be empty"));
		}

		return errors.size() == 0;
	}

	private boolean validate(DeliveryEditDto editDto, List<Error> errors) {

		if (isNull(editDto)) {
			errors.add(Error.create("deliveryDto", "deliveryDto cannot be null"));
			return false;
		}

		if (isNull(editDto.getId())) {
			errors.add(Error.create("id", "id cannot be null"));
			return false;
		}

		if (isEmpty(editDto.getContactId())) {
			errors.add(Error.create("contactId", "contactId cannot be empty"));
		}

		if (isEmpty(editDto.getStoreId())) {
			errors.add(Error.create("storeId", "storeId cannot be empty"));
		}

		if (isEmpty(editDto.getLabel())) {
			errors.add(Error.create("label", "label cannot be empty"));
		}

		if (isNull(editDto.getDateIn())) {
			errors.add(Error.create("dateIn", "dateIn cannot be null"));
		}

		int i = 0;

		for (ProductAddDto dto : editDto.getProductsToAdd()) {

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
				if (isEmpty(dto.getProductTax().getTaxId())) {
					errors.add(Error.create(prod + ".productTax.id", "productTax.id cannot be empty"));
				}

				if (isNull(dto.getProductTax().getFrom())) {
					errors.add(Error.create(prod + ".productTax.from", "productTax.taxFrom cannot be empty"));
				}
			}

			i++;
		}

		int j = 0;

		for (ProductEditDto dto : editDto.getProductsToEdit()) {

			String prod = "products[" + j + "]";

			if (isEmpty(dto.getId())) {
				errors.add(Error.create(prod + ".id", "id cannot be empty"));
			}

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
				if (isEmpty(dto.getProductTax().getTaxId())) {
					errors.add(Error.create(prod + ".productTax.id", "productTax.id cannot be empty"));
				}

				if (isNull(dto.getProductTax().getFrom())) {
					errors.add(Error.create(prod + ".productTax.taxFrom", "productTax.taxFrom cannot be empty"));
				}
			}

			j++;
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public DeliveryEditResponse edit(DeliveryEditRequest request)
			throws DeliveryServiceException, SessionServiceException {
	
		DeliveryEditResponse resp = new DeliveryEditResponse();

		sessionService.validate(request.getSessionDto());

		DeliveryEditDto editDto = request.getDeliveryDto() ;

		List<Error> errors = getEmptyErrors();
		if (!validate(editDto, errors)) {

			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
		}

		Delivery delivery = deliveriesDao.findById(editDto.getId());
		deliveryConverter.updateEntity(delivery, request.getDeliveryDto());
		deliveriesDao.saveOrUpdate(delivery);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");
		
		return resp;
	}

	@Transactional
	@Override
	public DeliveryDeleteResponse delete(DeliveryDeleteRequest request)
			throws SessionServiceException, DeliveryServiceException {

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
			resp.setMessage("error occured"); // TODO add localized msg
			return resp;
		}

		deliveriesDao.removeById(request.getDeliveryId());
		
		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");
		
		return resp;
	}
}
