package telephony.core.service;

import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public interface ComplaintCommentService extends BasicService<ComplaintComment>  {
	
	/**
	 * ad.
	 * @param session a.
	 * @param comment a. 
	 * @param complaintId a.
	 */
	void comment(Session session, ComplaintComment comment, long complaintId);
	
	/**
	 * asd.
	 * @param hashUnique a.
	 * @param comment a.
	 */
	void comment(String hashUnique, ComplaintComment comment);

}
