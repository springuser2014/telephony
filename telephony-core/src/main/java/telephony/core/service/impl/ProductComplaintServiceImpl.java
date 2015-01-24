package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.ProductComplaintConverter;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.ProductDetailedComplaintDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isEmpty;
import static telephony.core.assertion.CommonAssertions.isNull;

public class ProductComplaintServiceImpl
extends AbstractGenericService<ProductComplaint, ProductComplaintDao> 
implements ProductComplaintService {

	@Inject
	ProductComplaintConverter productComplaintConverter;

	@Inject
	SessionService sessionService;

	@Inject
	ProductComplaintDao productComplaintDao;

	public ProductComplaintServiceImpl() {
		super();
	}

	public ProductComplaintServiceImpl(Class<ProductComplaintDao> clazz) {
		super(clazz);
	}

	// TODO extract to validator
	private boolean validate(ProductComplaintDetailsFetchRequest request, List<Error> errors) {

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
	public ProductComplaintDetailsFetchResponse fetchDetails(ProductComplaintDetailsFetchRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.fetchDetails starts");
		ProductComplaintDetailsFetchResponse resp = new ProductComplaintDetailsFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.addError(Error.create("complaintId", "complaintId cannot be empty"));
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if(logger.isDebugEnabled()) {
			logger.debug("params : [ complaintId : {} ]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());

		ProductComplaint pc = productComplaintDao.findById(request.getComplaintId());
		ProductDetailedComplaintDto dto = productComplaintConverter.toDetailedDto(pc);

		resp.setDetailedComplaint(dto);
		resp.setMessage("operation performed successfully");
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ProductComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

	@Transactional
	@Override
	public ReportProductComplaintResponse report(ReportProductComplaintRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.report starts");
		ReportProductComplaintResponse resp = new ReportProductComplaintResponse();
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
		ProductComplaint entity = productComplaintConverter.toEntity(request.getComplaint());
		productComplaintDao.save(entity);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ReportProductComplaintRequest request, List<Error> errors) {

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

		if (isNull(request.getComplaint().getProductId())) {
			errors.add(Error.create("complaint.productId", "complaint.productid cannot be null"));
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
	private boolean validate(ProductComplaintFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ProductComplaintFetchResponse fetch(ProductComplaintFetchRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.fetch starts");
		ProductComplaintFetchResponse resp = new ProductComplaintFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError");
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto());

		List<ProductComplaintEditDto> complaintz = new ArrayList<ProductComplaintEditDto>();
		List<ProductComplaint> complaints = productComplaintDao.findByCriteria(request.getFilters());

		for (ProductComplaint entity : complaints) {
			complaintz.add(productComplaintConverter.toDto(entity));
		}

		resp.setComplaints(complaintz);
		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO move to external class
	private boolean validate(ProductComplaintEditRequest request, List<Error> errors) {

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
	public ProductComplaintEditResponse editComplaint(ProductComplaintEditRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.editComplaint starts");
		ProductComplaintEditResponse resp = new ProductComplaintEditResponse();
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

		ProductComplaint productComplaint = productComplaintDao.findById(request.getComplaint().getComplaintId());

		productComplaintConverter.update(productComplaint, request.getComplaint());

		productComplaintDao.saveOrUpdate(productComplaint);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");
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
		productComplaintDao.markAsRejected(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO : add localized msg
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		productComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();
		List<Error> errors = getEmptyErrors();

		// TODO extract to method
		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("errors occurred"); // TODO : add localized msg
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintid : {}]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		productComplaintDao.markAsAccepted(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ComplaintChangeStatusRequest request, List<Error> errors) {

		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));
		}

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");
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

		productComplaintDao.markAsResolved(request.getComplaintId());

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

		if (isEmpty(request.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest request) throws SessionServiceException {

		logger.info("ProductComplaintServiceImpl.deleteComplaint starts");
		ComplaintDeleteResponse resp = new ComplaintDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request, errors)) {
			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError"); // TODO add localized msg
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintId : {} ]", request.getComplaintId());
		}

		sessionService.validate(request.getSessionDto());
		productComplaintDao.removeById(request.getComplaintId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}
	
}
