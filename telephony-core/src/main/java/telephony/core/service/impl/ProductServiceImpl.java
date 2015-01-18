package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Producer;
import telephony.core.entity.jpa.Product;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.query.filter.ProductFilterCriteriaBuilder;
import telephony.core.service.converter.ModelConverter;
import telephony.core.service.converter.ProducerConverter;
import telephony.core.service.converter.ProductConverter;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.ProductService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.ProductFetchDto;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.isEmpty;

public class ProductServiceImpl
extends AbstractBasicService<Product> 
implements ProductService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductsDao productsDao;
	
	@Inject
	SessionService sessionService;

	@Inject
	ProductConverter productConverter;

	@Inject
	ProducerConverter producerConverter;

	@Inject
	ModelConverter modelConverter;

	@Override
	@Transactional
	public List<String> fetchAllColors(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllModels starts");

		sessionService.validate(session);

		List<String> res = new ArrayList<String>();
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria().build();
		// TODO : prepare separate JPQL for this case
		List<Product> products = productsDao.findByCriteria(filters);

		for (Product p : products) {
			if (!res.contains(p.getColor())) {
				res.add(p.getColor());
			}
		}

		return res;
	}

	@Transactional
	@Override
	public List<ProducerDto> fetchAllProducersInUse(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllProducersInUse starts");

		sessionService.validate(session);

		List<ProducerDto> res = new ArrayList<ProducerDto>();
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria().build();
		// TODO : prepare separate JPQL for this case
		List<Product> products = productsDao.findByCriteria(filters);

		for (Product p : products) {
			Producer pr = p.getModel().getProducer();

			ProducerDto dto = producerConverter.toProducerDto(pr);

			if (!res.contains(dto)) {
				res.add(dto);
			}
		}

		return res;
	}

	@Transactional
	@Override
	public List<String> fetchAllImeiInUse(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllImeiInUse starts");

		sessionService.validate(session);

		List<String> res = new ArrayList<String>();
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria().build();
		// TODO : prepare separate JPQL for this case
		List<Product> products = productsDao.findByCriteria(filters);

		for (Product p : products) {
			String imei = p.getImei();

			if (!res.contains(imei)) {
				res.add(imei);
			}
		}

		return res;
	}

	@Transactional
	@Override
	public List<ModelDto> fetchAllModelsInUse(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllImeiInUse starts");

		sessionService.validate(session);

		List<ModelDto> res = new ArrayList<>();
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria().build();
		// TODO : prepare separate JPQL for this case
		List<Product> products = productsDao.findByCriteria(filters);

		for (Product p : products) {
			Model m = p.getModel();

			ModelDto dto = modelConverter.toModelDto(m);

			if (!res.contains(dto)) {
				res.add(dto);
			}
		}

		return res;
	}

	@Transactional
	@Override
	public ProductDetailsResponse fetchDetails(ProductDetailsRequest request)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchDetails starts");

		ProductDetailsResponse resp = new ProductDetailsResponse();

		List<telephony.core.service.dto.response.Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {

			resp.setErrors(errors);
			resp.setMessage("validationFailed");
			resp.setSuccess(false);

			return resp;
		}

		sessionService.validate(request.getSessionDto());

		Product product = productsDao.findById(request.getProductId());
		ProductFetchDto productDto = productConverter.toProductFetchDto(product);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");
		resp.setProduct(productDto);

		return resp;
	}

	private boolean validate(ProductDetailsRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getProductId())) {
			errors.add(Error.create("productId", "productId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public long count(SessionDto session) throws SessionServiceException {

		sessionService.validate(session);

		return productsDao.count();
	}

	@Transactional
	@Override
	public ProductFetchResponse fetch(ProductFetchRequest req)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetch starts ");

		sessionService.validate(req.getSessionDto()); // TODO add some validation

		ProductFilterCriteria filters = req.getFilters();
		Object[] params = new Object[] { filters.getImei(), filters.getProducer(),
				filters.getModel(), filters.getColor(),
				filters.getStoreId(), filters.getDeliveryDateStart(),
				filters.getDeliveryDateEnd(), filters.getStatus()};

		if(logger.isDebugEnabled()) {
			logger.debug("params : [ imei : {} , producer : {} , model : {} , "
					+ "color : {} , storeId : {} , deliveryDateStart : {} , "
					+ "deliveryDateEnd : {}, productStatus : {} ] ", params);
		}

		List<Product> result = productsDao.findByCriteria(filters);
		
		List<ProductFetchDto> lst = new ArrayList<ProductFetchDto>();

		for(Product p : result) {
					
			lst.add(productConverter.toProductFetchDto(p));
		}

		ProductFetchResponse resp = new ProductFetchResponse();
		resp.setProducts(lst);
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

}
