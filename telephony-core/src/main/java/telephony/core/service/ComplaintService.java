package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 * @param <T> asd.
 */
public interface ComplaintService<T extends Complaint>  extends GenericService<T> {
	
	/**
	 * asd.
	 * @param hashUnique ad.
	 * @return asd.
	 */
	T findByHash(String hashUnique);
	
	/**
	 * asd.
	 * @param req a. 
	 * @return a.
	 */
	ReportComplaintResponse report(ReportComplaintRequest req);
	
	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 */
	ComplaintFetchResponse fetch(ComplaintFetchRequest req);
	
	/**
	 * asd.
	 * @param req a. 
	 * @return a.
	 */
	ComplaintEditResponse editComplaint(ComplaintEditRequest req);
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest req);
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest req);
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest req);
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest req);
	
	/**
	 * asd.
	 * @param req a.
	 */
	DeleteComplaintResponse deleteComplaint(DeleteComplaintRequest req);


	////////////////////////////////
	// TODO remove the stuff below
	////////////////////////////////

	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 */
	@Deprecated
	void report(SessionDto session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 * @return a.
	 */
	@Deprecated
	Complaint update(SessionDto session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaints a.
	 * @return a.
	 */
	@Deprecated
	Collection<T> update(SessionDto session, Collection<T> complaints);
	
	/**
	 * asd.
	 * @param session a.
	 * @param complaintId a.
	 */
	@Deprecated
	void markAsInProgress(SessionDto session, long complaintId);
	
	/**
	 * ads.
	 * @param session a.
	 * @param complaintId a.
	 */
	@Deprecated
	void markAsAccepted(SessionDto session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	@Deprecated
	void markAsRejected(SessionDto session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	@Deprecated
	void markAsResolved(SessionDto session, long complaintId);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaintId a.
	 */
	@Deprecated
	void removeById(SessionDto session, long complaintId);
	
	/**
	 * asd.
	 * @param complaintIds a.
	 * @param session asd.
	 */
	@Deprecated
	void removeByIds(SessionDto session, Collection<Long> complaintIds);
}
