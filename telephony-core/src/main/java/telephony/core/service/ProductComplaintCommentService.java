package telephony.core.service;

import telephony.core.entity.jpa.ProductComplaintComment;
import telephony.core.service.dto.request.AnonymousComplaintCommentRequest;
import telephony.core.service.dto.request.ComplaintCommentRequest;
import telephony.core.service.dto.response.AnonymousComplaintCommentResponse;
import telephony.core.service.dto.response.ComplaintCommentResponse;
import telephony.core.service.exception.SessionServiceException;

public interface ProductComplaintCommentService extends BasicService<ProductComplaintComment>  {

	ComplaintCommentResponse comment(ComplaintCommentRequest request)
			throws SessionServiceException;

	AnonymousComplaintCommentResponse comment(AnonymousComplaintCommentRequest request);
}
