package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.core.service.converter.StoreConverter;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.StoreDto;
import telephony.core.service.dto.StoreSearchDto;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.service.exception.StoreServiceException;

import static telephony.core.assertion.CommonAssertions.*;

public class StoreServiceImpl
extends AbstractBasicService<Store> 
implements StoreService {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    ProductsDao productsDao;

    @Inject
    DeliveriesDao deliveriesDao;
    
    @Inject
    StoresDao storesDao;
    
    @Inject
    SessionService sessionService;

    @Inject
	SalesDao salesDao;
 
    @Inject 
    UsersDao usersDao;
    
    @Inject
    RolesDao rolesDao;

	@Inject
	StoreConverter storeConverter;

	// TODO extract to validator
	private boolean validate(StoreFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be null"));
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if (isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public StoreFetchResponse fetch(StoreFetchRequest request)
			throws SessionServiceException, StoreServiceException {

		logger.info("StoreServiceImpl.fetch starts");
		StoreFetchResponse resp = new StoreFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<StoreSearchDto> storez = new ArrayList<StoreSearchDto>();
		List<Store> stores = storesDao.findByCriteria(request.getFilters());

		for (Store store: stores) {
			storez.add(storeConverter.toStoreSearchDto(store));
		}

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		resp.setStores(storez);
		return resp;
	}

	// TODO extract to validator
	private boolean validate(StoreAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getStoreDto())) {
			errors.add(Error.create("storeDto", "storeDto cannot be empty"));
		}

		// TODO add more cases

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public StoreAddResponse add(StoreAddRequest request) throws SessionServiceException, StoreServiceException {

		logger.info("StoreServiceImpl.add starts");
		StoreAddResponse resp = new StoreAddResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeDto : {} ]", request.getStoreDto());
		}

		sessionService.validate(request.getSessionDto());

		Store entity = storeConverter.toEntity(request.getStoreDto());

		storesDao.save(entity);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(StoreDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getStoreId())) {
			errors.add(Error.create("storeId", "storeId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public StoreDeleteResponse delete(StoreDeleteRequest request)
			throws SessionServiceException, StoreServiceException {

		logger.info("StoreServiceImpl.delete starts");
		StoreDeleteResponse resp = new StoreDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeId : {} ]", request.getStoreId());
		}

		sessionService.validate(request.getSessionDto());

		storesDao.removeById(request.getStoreId());

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(StoreEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getStoreDto())) {
			errors.add(Error.create("storeDto", "storeDto cannot be empty"));
			return false;
		}

		// TODO add more cases

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public StoreEditResponse edit(StoreEditRequest request)
			throws SessionServiceException, StoreServiceException {

		logger.info("StoreServiceImpl.edit starts");
		StoreEditResponse resp = new StoreEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeDto : {} ]", request.getStoreDto());
		}

		sessionService.validate(request.getSessionDto());

		Store entity = storesDao.findById(request.getStoreDto().getStoreId());
		storeConverter.updateEntity(request.getStoreDto(), entity);

		storesDao.save(entity);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

	@Override
	@Transactional
	public long count(SessionDto session) throws SessionServiceException {
		sessionService.validate(session); // TODO add validation

		return storesDao.count();
	}
}
