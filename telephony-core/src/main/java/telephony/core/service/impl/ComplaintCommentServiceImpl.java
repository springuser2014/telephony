package telephony.core.service.impl;

import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public class ComplaintCommentServiceImpl 
extends AbstractBasicService<ComplaintComment> 
implements ComplaintCommentService {

	@Override
	public void comment(Session session, ComplaintComment comment,
			long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comment(String hashUnique, ComplaintComment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
