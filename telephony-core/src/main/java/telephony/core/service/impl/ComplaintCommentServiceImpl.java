package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintCommentDao;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.SessionService;
import telephony.core.service.converter.ComplaintCommentConverter;
import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.AnonymousComplaintCommentRequest;
import telephony.core.service.dto.request.ComplaintCommentRequest;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.List;

import static telephony.core.assertion.CommonAssertions.isEmpty;

public class ComplaintCommentServiceImpl
extends AbstractBasicService<ComplaintComment> 
implements ComplaintCommentService {

	@Inject
	ComplaintCommentConverter complaintCommentConverter;

	@Inject
	SessionService sessionService;

	@Inject
	ProductComplaintService productComplaint;
	
	@Inject
	SaleComplaintService saleComplaint;
	
	@Inject
	ComplaintCommentDao complaintCommentDao;

	@Transactional
	@Override
	public ComplaintCommentResponse comment(ComplaintCommentRequest request) throws SessionServiceException {

		ComplaintCommentResponse resp = new ComplaintCommentResponse();

		sessionService.validate(request.getSessionDto());

		logger.info("ComplaintCommentServiceImpl.comments starts");
		List<Error> errors = getEmptyErrors();

		if (!validate(request.getComplaintComment(), errors)) {

			resp.setErrors(errors);
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(true);
			return resp;
		}

		ComplaintComment cc = complaintCommentConverter.toEntity(request.getComplaintComment());
		complaintCommentDao.save(cc);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

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
			resp.setMessage(""); // TODO add localized msg
			resp.setSuccess(true);
			return resp;
		}

		ComplaintComment cc = complaintCommentConverter.toEntity(request.getComplaintComment());
		complaintCommentDao.save(cc);

		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}


	@Transactional
	@Override
	public long count(SessionDto session) throws SessionServiceException {

		sessionService.validate(session);
		return complaintCommentDao.count();
	}
}
