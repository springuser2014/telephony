package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.SaleComplaintConverter;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;

public class SaleComplaintServiceImpl
extends AbstractGenericService<SaleComplaint, SaleComplaintDao>
implements SaleComplaintService {

	@Inject
	SessionService sessionService;

	@Inject
	SaleComplaintConverter saleComplaintConverter;

	@Inject
	SaleComplaintDao saleComplaintDao;

	public SaleComplaintServiceImpl() {
		super();
	}

	public SaleComplaintServiceImpl(Class<SaleComplaintDao> clazz) {
		super(clazz);
	}

	@Transactional
	@Override
	public SaleComplaintDetailsFetchResponse fetchDetails(SaleComplaintDetailsFetchRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.fetchDetails starts");
		SaleComplaintDetailsFetchResponse resp = new SaleComplaintDetailsFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(true);
			return resp;
		}

		if(logger.isDebugEnabled()) {
			logger.debug("params : [ complaintId : {} ]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		SaleComplaint pc = saleComplaintDao.findById(request.getComplaintId());
		SaleDetailedComplaintDto dto = saleComplaintConverter.toDetailedDto(pc);

		resp.setDetailedComplaint(dto);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(SaleComplaintDetailsFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be empty"));
		}

		return errors.size() == 0;
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

		logger.info("ProductComplaintServiceImpl.report starts");
		ReportSaleComplaintResponse resp = new ReportSaleComplaintResponse();
		List<Error> errors = getEmptyErrors();

		if(!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintDto : {} ]", request.getComplaint());
		}

		sessionService.validate(request.getSessionDto());

		SaleComplaint entity = saleComplaintConverter.toEntity(request.getComplaint());

		saleComplaintDao.save(entity);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	/// TODO extract to validator
	private boolean validate(ReportSaleComplaintRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getComplaint())) {
			errors.add(Error.create("complaint", "complaint cannot be null"));
			return false;
		}

		if (isNull(request.getComplaint().getSaleId())) {
			errors.add(Error.create("complaint.saleId", "complaint.saleId cannot be null"));
		}

		if (isEmpty(request.getComplaint().getDescription())) {
			errors.add(Error.create("complaint.description", "complaint.description cannot be empty"));
		}

		if (isEmpty(request.getComplaint().getTitle())) {
			errors.add(Error.create("complaint.title", "complaint.title cannot be empty"));
		}

		if (isNull(request.getComplaint().getReportedDate())) {
			errors.add(Error.create("complaint.reportedDate", "complaint.reportedDate cannot be null"));
		}

		if (isNull(request.getComplaint().getStatus())) {
			errors.add(Error.create("complaint.status", "complaint.status cannot be null"));
		}

		if (isEmpty(request.getComplaint().getUniqueHash())) {
			errors.add(Error.create("complaint.uniqueHash", "complaint.uniqueHash cannot be empty"));
		}

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(SaleComplaintFetchRequest request, List<Error> errors) {

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

	// TODO move to abstractGenericService
	@Transactional
	@Override
	public SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.fetch starts");
		SaleComplaintFetchResponse resp = new SaleComplaintFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<SaleComplaintEditDto> complaintz = new ArrayList<SaleComplaintEditDto>();
		List<SaleComplaint> complaints = saleComplaintDao.findByCriteria(request.getFilters());

		for (SaleComplaint entity : complaints) {
			complaintz.add(saleComplaintConverter.toDto(entity));
		}

		resp.setComplaints(complaintz);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public SaleComplaintEditResponse editComplaint(SaleComplaintEditRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.editComplaint starts");
		SaleComplaintEditResponse resp = new SaleComplaintEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintEdit : {} ] ", request.getComplaint());
		}

		sessionService.validate(request.getSessionDto());

		SaleComplaint saleComplaint = saleComplaintDao.findById(request.getComplaint().getComplaintId());

		saleComplaintConverter.update(saleComplaint, request.getComplaint());

		saleComplaintDao.saveOrUpdate(saleComplaint);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO move to external class
	private boolean validate(SaleComplaintEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getComplaint())) {
			errors.add(Error.create("complaint", "complaint cannot be null"));
			return false;
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.markAsRejected starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO : add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		saleComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage("operation performed succcessfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.markAsInProgress starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError");
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		saleComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.markAsAccepted starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO : add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		saleComplaintDao.markAsAccepted(request.getComplaintId());

		resp.setMessage("operation perfomed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ComplaintChangeStatusRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.markAsAccepted starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO : add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		saleComplaintDao.markAsResolved(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ComplaintDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest request) throws SessionServiceException {

		logger.info("SaleComplaintServiceImpl.deleteComplaint starts");
		ComplaintDeleteResponse resp = new ComplaintDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintId : {} ]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		saleComplaintDao.removeById(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

}
