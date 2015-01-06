package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.SaleComplaintConverter;
import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.List;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;

/**
 * asd.
 */
public class SaleComplaintServiceImpl 
extends AbstractGenericService<SaleComplaint, SaleComplaintDao>
implements SaleComplaintService {

	@Inject
	SessionService sessionService;

	@Inject
	SaleComplaintConverter saleComplaintConverter;

	@Inject
	SaleComplaintDao saleComplaintDao;

	/**
	 * asd.
	 */
	public SaleComplaintServiceImpl() {
		super();
	}

	/**
	 * asd.
	 * @param clazz a.
	 */
	public SaleComplaintServiceImpl(Class<SaleComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public SaleComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

	// TODO move to abstractGenericService
	@Transactional
	@Override
	public ReportSaleComplaintResponse report(ReportSaleComplaintRequest request) throws SessionServiceException {

		ReportSaleComplaintResponse resp = new ReportSaleComplaintResponse();

		logger.info("ProductComplaintServiceImpl.report starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintDto : {} ]", request.getComplaint());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation
		List<Error> errors = getEmptyErrors();

		if(!validate(request.getComplaint(), errors)) {
			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		SaleComplaint entity = saleComplaintConverter.toEntity(request.getComplaint());

		saleComplaintDao.save(entity);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}


	private boolean validate(SaleComplaintDto complaint, List<Error> errors) {

		if (isNull(complaint.getSaleId())) {
			errors.add(Error.create("saleId", "saleId cannot be null"));
		}

		if (isEmpty(complaint.getDescription())) {
			errors.add(Error.create("description", "description cannot be empty"));
		}

		if (isEmpty(complaint.getTitle())) {
			errors.add(Error.create("title", "title cannot be empty"));
		}

		if (isNull(complaint.getReportedDate())) {
			errors.add(Error.create("reportedDate", "reportedDate cannot be null"));
		}

		if (isNull(complaint.getStatus())) {
			errors.add(Error.create("status", "status cannot be null"));
		}

		if (isEmpty(complaint.getUniqueHash())) {
			errors.add(Error.create("uniqueHash", "uniqueHash cannot be empty"));
		}

		return errors.size() == 0;
	}


	@Transactional
	@Override
	public SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest saleComplaintFetchRequest) throws SessionServiceException {
		return null;
	}

	// TODO : make this method generic
	@Transactional
	@Override
	public SaleComplaintEditResponse editComplaint(SaleComplaintEditRequest request) throws SessionServiceException {
		SaleComplaintEditResponse resp = new SaleComplaintEditResponse();

		logger.info("SaleComplaintServiceImpl.editComplaint starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintEdit : {} ] ", request.getComplaint());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		if (!validate(request.getComplaint(), errors)) {
			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		SaleComplaint saleComplaint = saleComplaintDao.findById(request.getComplaint().getComplaintId());

		saleComplaintConverter.update(saleComplaint, request.getComplaint());

		saleComplaintDao.saveOrUpdate(saleComplaint);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO move to external class
	private boolean validate(SaleComplaintEditDto dto, List<Error> errors) {

		if (isNull(dto.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaitId cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("SaleComplaintServiceImpl.markAsRejected starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		// TODO extract to method
		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO : add localized msg
		}

		saleComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("SaleComplaintServiceImpl.markAsInProgress starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		// TODO extract to method
		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO : add localized msg
		}

		saleComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("SaleComplaintServiceImpl.markAsAccepted starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		// TODO extract to method
		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO : add localized msg
		}

		saleComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("SaleComplaintServiceImpl.markAsAccepted starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		// TODO extract to method
		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO : add localized msg
		}

		saleComplaintDao.markAsResolved(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest request) throws SessionServiceException {
		ComplaintDeleteResponse resp = new ComplaintDeleteResponse();

		logger.info("SaleComplaintServiceImpl.deleteComplaint starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintId : {} ]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage(""); // TODO add localized msg
		}

		saleComplaintDao.removeById(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

}
