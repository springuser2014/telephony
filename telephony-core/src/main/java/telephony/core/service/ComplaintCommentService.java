package telephony.core.service;

import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.service.dto.SessionDto;

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
	@Deprecated
	void comment(SessionDto session, ComplaintComment comment, long complaintId);
	
	/**
	 * asd.
	 * @param hashUnique a.
	 * @param comment a.
	 */
	@Deprecated
	void comment(String hashUnique, ComplaintComment comment);

}
