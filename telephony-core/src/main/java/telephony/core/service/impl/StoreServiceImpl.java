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
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreDeleteResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.service.exception.StoreServiceException;


/**
 * Stores management service.
 */
public class StoreServiceImpl 
extends AbstractBasicService<Store> 
implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private ProductsDao productsDao;

    @Inject
    private DeliveriesDao deliveriesDao;
    
    @Inject
    private StoresDao storesDao;
    
    @Inject
    private SessionService sessionService;

    @Inject
	private SalesDao salesDao;
 
    @Inject 
    private UsersDao usersDao;
    
    @Inject
    private RolesDao rolesDao;

	@Override
	@Transactional
	public StoreFetchResponse fetch(StoreFetchRequest request) throws SessionServiceException, StoreServiceException {
		logger.info("StoreServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		List<StoreDto> storez = new ArrayList<StoreDto>();
		List<Store> stores = storesDao.find(request.getFilters());

		for (Store store: stores) {
			storez.add(StoreConverter.toDto(store));
		}

		StoreFetchResponse resp = new StoreFetchResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		resp.setStores(storez);
		return resp;
	}

	@Transactional
	@Override
	public StoreAddResponse add(StoreAddRequest request) throws SessionServiceException, StoreServiceException {
		logger.info("StoreServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeDto : {} ]", request.getStoreDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		Store entity = StoreConverter.toEntity(request.getStoreDto());

		storesDao.save(entity);

		StoreAddResponse resp = new StoreAddResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg

		return resp;
	}

	@Transactional
	@Override
	public StoreDeleteResponse delete(StoreDeleteRequest request) throws SessionServiceException, StoreServiceException {
		logger.info("StoreServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeId : {} ]", request.getStoreId());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		storesDao.removeById(request.getStoreId());

		StoreDeleteResponse resp = new StoreDeleteResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg

		return resp;
	}

	@Transactional
	@Override
	public StoreEditResponse edit(StoreEditRequest request) throws SessionServiceException, StoreServiceException {
		logger.info("StoreServiceImpl.edit starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ storeDto : {} ]", request.getStoreDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		Store entity = storesDao.findById(request.getStoreDto().getStoreId());
		StoreConverter.updateEntity(request.getStoreDto(), entity);

		storesDao.save(entity);

		StoreEditResponse resp = new StoreEditResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg

		return resp;
	}

	@Override
	@Transactional
	public long count(SessionDto session) throws SessionServiceException {
		sessionService.validate(session); // TODO add validation

		return storesDao.count();
	}
}
