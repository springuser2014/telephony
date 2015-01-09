package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.SalesDao;
import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.SaleFilterCriteria;
import telephony.core.query.filter.SaleFilterCriteriaBuilder;
import telephony.core.service.SaleService;
import telephony.core.service.SessionService;
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

/**
 * Sales management services.
 */
public class SaleServiceImpl 
extends AbstractBasicService<Sale> 
implements SaleService {

	@Inject
	SalesDao salesDao;

	@Inject
	ProductsDao productsDao;
	
	@Inject 
	ContactsDao contactsDao;
	
	@Inject
	StoresDao storesDao;
	
	@Inject
	SessionService sessionService;

	@Inject
	SaleConverter saleConverter;

	Logger logger = LoggerFactory.getLogger(getClass());

	@Transactional
	@Override
	public SaleDetailsResponse fetchDetails(SaleDetailsRequest request) throws SessionServiceException {

		logger.info("SaleServiceImpl.fetchDetails starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleId : {} ] ", request.getSaleId());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		Sale sale = salesDao.findById(request.getSaleId());
		DetailedSaleDto detailedSale = saleConverter.toDetailedSaleDto(sale);

		SaleDetailsResponse resp = new SaleDetailsResponse();
		resp.setDetailedSale(detailedSale);
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg
		return resp;
	}

	@Transactional
	@Override
	public SalesFetchResponse findSales(SalesFetchRequest request) throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.findSales starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ] ", request.getFilters());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		List<SaleDto> salez = new ArrayList<SaleDto>();
		List<Sale> sales = salesDao.find(request.getFilters());

		for (Sale sale : sales) {
			salez.add(saleConverter.toSaleDto(sale));
		}

		SalesFetchResponse resp = new SalesFetchResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		resp.setSales(salez);
		return resp;
	}

	@Transactional
	@Override
	public SaleAddResponse add(SaleAddRequest request) throws SessionServiceException, SaleServiceException {

		SaleAddResponse resp = new SaleAddResponse();

		logger.info("SaleServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleDto : {} ] ", request.getSale());
		}

		List<Error> errors = getEmptyErrors();

		if (!validate(request.getSale(), errors)) { // TODO validation service? custom class?
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO add localized msg
			return resp;
		}

		sessionService.validate(request.getSessionDto());

		Sale entity = saleConverter.toEntity(request.getSale());

		salesDao.save(entity);

		resp.setMessage("successfully added a new sale"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO : extract to service?
	private boolean validate(SaleEditDto saleEdit, List<Error> errors) {

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

	// TODO : extract to service?
	private boolean validate(SaleAddDto sale, List<Error> errors) {

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

		sessionService.validate(request.getSessionDto()); //
		List<Error> errors = getEmptyErrors();

		if (!validate(request.getSaleEdit(), errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occured"); // TODO add localized msg
			return resp;
		}

		SaleEditDto editDto = request.getSaleEdit();

		Sale sale = salesDao.findById(editDto.getSaleId());

		saleConverter.updateEntity(sale, editDto);

		salesDao.saveOrUpdate(sale);

		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg

		return resp;
	}

	@Transactional
	@Override
	public SaleDeleteResponse delete(SaleDeleteRequest request) throws SessionServiceException, SaleServiceException {

		logger.info("SaleServiceImpl.delete starts");

		SaleDeleteResponse resp = new SaleDeleteResponse();

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ saleId : {} ]", request.getSaleId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		if (!validate(request.getSaleId(), errors)) {
			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
		}

		salesDao.removeById(request.getSaleId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO : extract to service?
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
		sessionService.validate(session);
		return salesDao.count();
	}
}
