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
public class ProductComplaintServiceImpl 
extends AbstractGenericService<ProductComplaint, ProductComplaintDao> 
implements ProductComplaintService {

	@Inject
	ProductComplaintConverter productComplaintConverter;

	@Inject
	SessionService sessionService;

	@Inject
	ProductComplaintDao productComplaintDao;

	/**
	 * asd.
	 */
	public ProductComplaintServiceImpl() {
		super();
	}

	/**
	 * sad.
	 * @param clazz a.
	 */
	public ProductComplaintServiceImpl(Class<ProductComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public ProductComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

	@Transactional
	@Override
	public ReportComplaintResponse report(ReportComplaintRequest request) throws SessionServiceException {

		ReportComplaintResponse resp = new ReportComplaintResponse();

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

		ProductComplaint entity = productComplaintConverter.toEntity(request.getComplaint());

		productComplaintDao.save(entity);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	private boolean validate(ProductComplaintDto complaint, List<Error> errors) {

		if (isNull(complaint.getProductId())) {
			errors.add(Error.create("productId", "productid cannot be null"));
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

	@Override
	public ComplaintFetchResponse fetch(ComplaintFetchRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO move to external class
	private boolean validate(ProductComplaintEditDto dto, List<Error> errors) {

		if (isNull(dto.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public ProductComplaintEditResponse editComplaint(ProductComplaintEditRequest request) throws SessionServiceException {

		ProductComplaintEditResponse resp = new ProductComplaintEditResponse();

		logger.info("ProductComplaintServiceImpl.editComplaint starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ complaintEdit : {} ] ", request.getComplaintEditDto());
		}

		sessionService.validate(request.getSessionDto());
		List<Error> errors = getEmptyErrors();

		if (!validate(request.getComplaintEditDto(), errors)) {
			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(false);
			return resp;
		}

		ProductComplaint productComplaint = productComplaintDao.findById(request.getComplaintEditDto().getComplaintId());

		productComplaintConverter.update(productComplaint, request.getComplaintEditDto());

		productComplaintDao.saveOrUpdate(productComplaint);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");

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
			return resp;
		}

		productComplaintDao.markAsRejected(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest request) throws SessionServiceException {

		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");

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

		productComplaintDao.markAsInProgress(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");

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
			return resp;
		}

		productComplaintDao.markAsAccepted(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest request) throws SessionServiceException {
		ComplaintChangeStatusResponse resp = new ComplaintChangeStatusResponse();

		logger.info("ProductComplaintServiceImpl.markAsInProgress starts");

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
			return resp;
		}

		productComplaintDao.markAsResolved(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest request) throws SessionServiceException {

		ComplaintDeleteResponse resp = new ComplaintDeleteResponse();

		logger.info("ProductComplaintServiceImpl.deleteComplaint starts");

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

		productComplaintDao.removeById(request.getComplaintId());

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}
	
}
