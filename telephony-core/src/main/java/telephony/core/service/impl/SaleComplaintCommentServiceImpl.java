package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import telephony.core.dao.ProductComplaintCommentDao;
import telephony.core.dao.SaleComplaintCommentDao;
import telephony.core.entity.jpa.ProductComplaintComment;
import telephony.core.entity.jpa.SaleComplaintComment;
import telephony.core.service.*;
import telephony.core.service.converter.ComplaintCommentConverter;
import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.AnonymousComplaintCommentRequest;
import telephony.core.service.dto.request.ComplaintCommentRequest;
import telephony.core.service.dto.response.AnonymousComplaintCommentResponse;
import telephony.core.service.dto.response.ComplaintCommentResponse;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.List;

import static telephony.core.assertion.CommonAssertions.isEmpty;

public class SaleComplaintCommentServiceImpl
extends AbstractBasicService<SaleComplaintComment>
implements SaleComplaintCommentService {

	@Inject
	ComplaintCommentConverter complaintCommentConverter;

	@Inject
	SessionManager sessionManager;

	@Inject
	SaleComplaintService saleComplaint;
	
	@Inject
	SaleComplaintCommentDao complaintCommentDao;

	@Transactional
	@Override
	public ComplaintCommentResponse comment(ComplaintCommentRequest request) throws SessionServiceException {

		ComplaintCommentResponse resp = new ComplaintCommentResponse();

		logger.info("ComplaintCommentServiceImpl.comments starts");

		List<Error> errors = getEmptyErrors();

		if (!validate(request.getComplaintComment(), errors)) {

			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(true);
			return resp;
		}

		sessionManager.validate(request.getSessionDto());

		SaleComplaintComment cc = complaintCommentConverter.toSaleComplaintEntity(request.getComplaintComment());
		complaintCommentDao.save(cc);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(ComplaintCommentDto complaintComment, List<Error> errors) {

		if(isEmpty(complaintComment.getComplaintId())) {
			errors.add(Error.create("complaintId", "complaintId cannot be empty"));
		}

		if (isEmpty(complaintComment.getAuthor())) {
			errors.add(Error.create("author", "author cannot be empty"));
		}

		if (isEmpty(complaintComment.getComment())) {
			errors.add(Error.create("comment", "comment cannot be empty"));
		}

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(AnonymousComplaintCommentDto complaintComment, List<Error> errors) {

		if(isEmpty(complaintComment.getHashUnique())) {
			errors.add(Error.create("hashUnique", "hashUnique cannot be empty"));
		}

		if (isEmpty(complaintComment.getAuthor())) {
			errors.add(Error.create("author", "author cannot be empty"));
		}

		if (isEmpty(complaintComment.getComment())) {
			errors.add(Error.create("comment", "comment cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public AnonymousComplaintCommentResponse comment(AnonymousComplaintCommentRequest request) {

		AnonymousComplaintCommentResponse resp = new AnonymousComplaintCommentResponse();

		logger.info("ComplaintCommentServiceImpl.comments starts");

		List<Error> errors = getEmptyErrors();

		if (!validate(request.getComplaintComment(), errors)) {

			resp.setErrors(errors);
			resp.setMessage("validationError"); // TODO add localized msg
			resp.setSuccess(true);
			return resp;
		}

		SaleComplaintComment cc = complaintCommentConverter.toSaleComplaintEntity(request.getComplaintComment());
		complaintCommentDao.save(cc);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public long count(SessionDto session) throws SessionServiceException {

		sessionManager.validate(session);
		return complaintCommentDao.count();
	}
}
