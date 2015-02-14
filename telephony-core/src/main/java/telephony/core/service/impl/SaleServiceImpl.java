package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.*;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.User;
import telephony.core.service.SaleService;
import telephony.core.service.SessionManager;
import telephony.core.service.converter.SaleConverter;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import static telephony.core.assertion.CommonAssertions.*;

public class SaleServiceImpl
extends AbstractBasicService<Sale> 
implements SaleService {

	@Inject
	UsersDao usersDao;

	@Inject
	SalesDao salesDao;

	@Inject
	ProductsDao productsDao;
	
	@Inject 
	ContactsDao contactsDao;
	
	@Inject
	StoresDao storesDao;
	
	@Inject
	SessionManager sessionManager;

	@Inject
	SaleConverter saleConverter;

	Logger logger = LoggerFactory.getLogger(getClass());

	// TODO extract to validator
	private boolean validate(SaleDetailsRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getSaleId())) {
			errors.add(Error.create("saleId", "saleId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public SaleDetailsResponse fetchDetails(SaleDetailsRequest request) throws SessionServiceException {

		logger.info("SaleServiceImpl.fetchDetails starts");
		SaleDetailsResponse resp = new SaleDetailsResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleId : {} ] ", request.getSaleId());
		}

		sessionManager.validate(request.getSessionDto());

		Sale sale = salesDao.findById(request.getSaleId());
		DetailedSaleDto detailedSale = saleConverter.toDetailedSaleDto(sale);

		resp.setDetailedSale(detailedSale);
		resp.setSuccess(true);
		resp.setMessage("operation performed succesfully"); // TODO add localized msg
		return resp;
	}

	// TODO extract to validator
	private boolean validate(SalesFetchRequest request, List<Error> errors) {

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

	@Transactional
	@Override
	public SalesFetchResponse findSales(SalesFetchRequest request) throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.findSales starts");
		SalesFetchResponse resp = new SalesFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ] ", request.getFilters());
		}

		sessionManager.validate(request.getSessionDto());

		List<SaleSearchDto> salez = new ArrayList<>();
		List<Sale> sales = salesDao.findByCriteria(request.getFilters());
		Long count = salesDao.countByCriteria(request.getFilters());

		for (Sale sale : sales) {
			salez.add(saleConverter.toSaleSearchDto(sale));
		}

		resp.setCountTotal(count);
		resp.setMessage("operation perfromed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		resp.setSales(salez);
		return resp;
	}

	@Override
	@Transactional
	public SaleAddResponse add(SaleAddRequest request)
			throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.add starts");
		List<Error> errors = getEmptyErrors();
		SaleAddResponse resp = new SaleAddResponse();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleDto : {} ] ", request.getSale());
		}

		sessionManager.validate(request.getSessionDto());

		User u = usersDao.findByName(request.getUsername());

		Sale entity = saleConverter.toEntity(request.getSale());
		entity.setUser(u);

		salesDao.save(entity);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO : extract to validator
	private boolean validate(SaleEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getSaleEdit())) {
			errors.add(Error.create("saleDto", "saleDto cannot be null"));
		}

		SaleEditDto saleEdit = request.getSaleEdit();

		for (Long productId : saleEdit.getProductsToAdd()) {

			if (isNull(productId)) {
				errors.add(Error.create("global.warning", "productId cannot be null"));
				continue;
			}

			if(!productsDao.checkIfProductIsAvailable(productId)) {
				errors.add(Error.create("productsToAdd." + productId, "already sold"));
			}
		}

		for (Long productId : saleEdit.getProductsToRemove()) {

			if (isNull(productId)) {
				errors.add(Error.create("global.warning", "productId cannot be null"));
				continue;
			}

			if(!productsDao.checkIfProductIsAssignedToSale(productId, saleEdit.getSaleId())) {
				errors.add(Error.create("productsToRemove." + productId, "not assigned to given sale"));
			}
		}

		return errors.size() == 0;
	}

	// TODO : extract to validator
	private boolean validate(SaleAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getSale())) {
			errors.add(Error.create("saleDto", "saleDto cannot be null"));
			return false;
		}

		SaleAddDto sale = request.getSale();

		for (Long productId : sale.getProductsIds()) {

			if (isNull(productId)) {
				errors.add(Error.create("global.warning", "productId cannot be null"));
				continue;
			}

			if(!productsDao.checkIfProductIsAvailable(productId)) {
				errors.add(Error.create("productsIds." + productId, "already sold"));
			}
		}

		if (isNull(sale.getContactId())) {
			errors.add(Error.create("contactId", "contactId cannot be null"));
		}

		if (isNull(sale.getStoreId())) {
			errors.add(Error.create("storeId", "storeId cannot be null"));
		}

		if (isNull(sale.getLabel())) {
			errors.add(Error.create("label", "label cannot be null"));
		}

		if (isNull(sale.getDateOut())) {
			errors.add(Error.create("dateOut", "dateOut cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public SaleEditResponse edit(SaleEditRequest request) throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.edit starts");
		SaleEditResponse resp = new SaleEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO add localized msg
			return resp;
		}

		sessionManager.validate(request.getSessionDto());

		SaleEditDto editDto = request.getSaleEdit();

		Sale sale = salesDao.findById(editDto.getSaleId());

		saleConverter.updateEntity(sale, editDto);

		salesDao.saveOrUpdate(sale);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

	// TODO extract to validator
	private boolean validate(SaleDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getSaleId())) {
			errors.add(Error.create("saleId", "saleId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public SaleDeleteResponse delete(SaleDeleteRequest request) throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.delete starts");
		SaleDeleteResponse resp = new SaleDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleId : {} ]", request.getSaleId());
		}

		sessionManager.validate(request.getSessionDto());

		salesDao.removeById(request.getSaleId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO : extract to validator
	private boolean validate(Long saleId, List<Error> errors) {

		if (isNull(saleId)) {
			errors.add(Error.create("saleId", "saleId cannot be null"));
		}

		Sale sale = salesDao.findById(saleId);

		if (isNull(sale)) {
			errors.add(Error.create("saleId", "there is no entity with given id"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public long count(SessionDto session) throws SessionServiceException {
		sessionManager.validate(session);
		return salesDao.count();
	}
}
