package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.dao.ProducerDao;
import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Model;
import telephony.core.entity.jpa.Producer;
import telephony.core.entity.jpa.Product;
import telephony.core.query.filter.*;
import telephony.core.service.converter.ModelConverter;
import telephony.core.service.converter.ProducerConverter;
import telephony.core.service.converter.ProductConverter;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.request.ProductCheckImeiRequest;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.request.ProductFetchDataRequest;
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

import static telephony.core.assertion.CommonAssertions.*;

public class ProductServiceImpl
extends AbstractBasicService<Product> 
implements ProductService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductsDao productsDao;

	@Inject
	ModelDao modelDao;

	@Inject
	ProducerDao producerDao;
	
	@Inject
	SessionService sessionService;

	@Inject
	ProductConverter productConverter;

	@Inject
	ProducerConverter producerConverter;

	@Inject
	ModelConverter modelConverter;

	//  TODO extract to validator
	private boolean validate(ProductFetchDataRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ProductFetchDataResponse fetchData(ProductFetchDataRequest request)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchData starts");
		ProductFetchDataResponse resp = new ProductFetchDataResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		sessionService.validate(request.getSessionDto());

		ModelFilterCriteria modelFilterCriteria = ModelFilterCriteriaBuilder.modelFilterCriteria()
				.withPage(0).withPerPage(1000)
				.build();

		ProducerFilterCriteria producerFilterCriteria = ProducerFilterCriteriaBuilder.producerFilterCriteria()
				.withPage(0).withPerPage(1000)
				.build();

		List<String> colors = productsDao.fetchColorsList();
		List<Model> models = modelDao.find(modelFilterCriteria);
		List<Producer> producers = producerDao.fetch(producerFilterCriteria);

		List<ModelDto> m = new ArrayList<>();
		List<ProducerDto> p = new ArrayList<>();

		for(Model model : models) {
			m.add(modelConverter.toModelDto(model));
		}

		for (Producer producer : producers) {
			p.add(producerConverter.toProducerDto(producer));
		}

		resp.setProducers(p);
		resp.setModels(m);
		resp.setColors(colors);
		resp.setSuccess(true);
		resp.setMessage("operation performed successfully");
		return resp;
	}

	// TODO add request and response objs
	@Override
	@Transactional
	public List<String> fetchAllColors(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllModels starts");

		sessionService.validate(session);

		List<String> res = new ArrayList<>();
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

	// TODO add request and response objs
	@Transactional
	@Override
	public List<ProducerDto> fetchAllProducersInUse(SessionDto session)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllProducersInUse starts");

		sessionService.validate(session);

		List<ProducerDto> res = new ArrayList<>();
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

	@Override
	public List<String> fetchAllImeiInUse(SessionDto session) throws SessionServiceException {
		return null;
	}

	// TODO extract to validator
	private boolean validate(ProductCheckImeiRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getImei())) {
			errors.add(Error.create("imiei", "imei cannot be empty"));
			return false;
		}

		if (request.getImei().trim().length() != 15) {
			errors.add(Error.create("imiei", "imei should be 15 chars length"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public ProductCheckImeiResponse checkImei(ProductCheckImeiRequest request)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetchAllImeiInUse starts");
		ProductCheckImeiResponse resp = new ProductCheckImeiResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		sessionService.validate(request.getSessionDto());

		List<String> imeis = new ArrayList<>();
		ProductFilterCriteria filters = ProductFilterCriteriaBuilder.productFilterCriteria().build();
		// TODO : prepare separate JPQL for this case
		List<Product> products = productsDao.findByCriteria(filters);

		for (Product p : products) {
			String imei = p.getImei();

			if (!imeis.contains(imei)) {
				imeis.add(imei);
			}
		}

		resp.setIsAvailable(!imeis.contains(request.getImei()));
		resp.setMessage("operation performed successfully");
		resp.setSuccess(true);
		return resp;
	}

	// TODO add request and response objs
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

	// TODO extract to validation
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

	// TODO extract to validator
	private boolean validate(ProductFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be empty"));
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if (isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ProductFetchResponse fetch(ProductFetchRequest request)
			throws SessionServiceException {

		logger.info("ProductServiceImpl.fetch starts ");
		ProductFetchResponse resp = new ProductFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		ProductFilterCriteria filters = request.getFilters();
		Object[] params = new Object[] { filters.getImei(), filters.getProducer(),
				filters.getModel(), filters.getColor(),
				filters.getStoreId(), filters.getDeliveryDateStart(),
				filters.getDeliveryDateEnd(), filters.getStatus()};

		if(logger.isDebugEnabled()) {
			logger.debug("params : [ imei : {} , producer : {} , model : {} , "
					+ "color : {} , storeId : {} , deliveryDateStart : {} , "
					+ "deliveryDateEnd : {}, productStatus : {} ] ", params);
		}

		sessionService.validate(request.getSessionDto());

		List<Product> result = productsDao.findByCriteria(filters);
		
		List<ProductFetchDto> lst = new ArrayList<>();

		for(Product p : result) {
			lst.add(productConverter.toProductFetchDto(p));
		}

		resp.setProducts(lst);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

}
