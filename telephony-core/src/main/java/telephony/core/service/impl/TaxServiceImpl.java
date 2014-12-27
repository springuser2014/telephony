package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Tax;
import telephony.core.service.SessionService;
import telephony.core.service.TaxService;
import telephony.core.service.converter.TaxConverter;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.TaxDto;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxAddResponse;
import telephony.core.service.dto.response.TaxDeleteResponse;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.dto.response.TaxFetchResponse;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public class TaxServiceImpl
extends AbstractBasicService<Tax>
implements TaxService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	TaxDao taxDao;

	@Inject
	SessionService sessionService;

	@Inject
	TaxConverter taxConverter;

	@Transactional
	@Override
	public long count(SessionDto session) {
		return taxDao.count();
	}

	@Override
	@Transactional
	public TaxFetchResponse fetch(TaxFetchRequest request) throws SessionServiceException {
		logger.info("TaxServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ request : {} ]", request);
		}

		sessionService.validate(request.getSessionDto());

		List<TaxDto> taxez = new ArrayList<TaxDto>();
		List<Tax> entities = taxDao.fetch(request.getFilters());

		for (Tax entity : entities) {
			taxez.add(taxConverter.toDto(entity));
		}

		TaxFetchResponse resp = new TaxFetchResponse();
		resp.setMessage(""); // TODO : add localized msg
		resp.setSuccess(true);
		resp.setTaxes(taxez);

		return resp;
	}

	@Override
	@Transactional
	public TaxAddResponse add(TaxAddRequest request) throws SessionServiceException {
		logger.info("TaxServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ taxDto : {} ]", request.getTaxDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		TaxDto dto = request.getTaxDto();
		Tax entity = taxConverter.toEntity(dto);

		taxDao.save(entity);

		TaxAddResponse resp = new TaxAddResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO : add localized msg

		return resp;
	}

	@Override
	@Transactional
	public TaxEditResponse edit(TaxEditRequest request) throws SessionServiceException {
		logger.info("TaxServiceImpl.edit starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ editTax : {} ]", request.getTaxDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add vlaidation

		TaxDto dto = request.getTaxDto();
		Tax tax = taxDao.findById(dto.getId());

		taxConverter.updateEntity(dto, tax);

		taxDao.saveOrUpdate(tax);

		TaxEditResponse resp = new TaxEditResponse();
		resp.setMessage(""); // TODO : add localized msg
		resp.setSuccess(true);

		return resp;

	}

	@Override
	@Transactional
	public TaxDeleteResponse delete(TaxDeleteRequest request) throws SessionServiceException {
		logger.info("TaxServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ taxId : {} ] ", request.getTaxId());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		taxDao.removeById(request.getTaxId());

		TaxDeleteResponse resp = new TaxDeleteResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

}
