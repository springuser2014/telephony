package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 * @param <T> asd.
 */
public interface ComplaintService<T extends Complaint>  extends GenericService<T>  {
	
	/**
	 * asd.
	 * @param hashUnique ad.
	 * @return asd.
	 */
	T findByHash(String hashUnique);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 */
	void report(SessionDto session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 * @return a.
	 */
	Complaint update(SessionDto session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaints a.
	 * @return a.
	 */
	Collection<T> update(SessionDto session, Collection<T> complaints);
	
	/**
	 * asd.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsInProgress(SessionDto session, long complaintId);
	
	/**
	 * ads.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsAccepted(SessionDto session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsRejected(SessionDto session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsResolved(SessionDto session, long complaintId);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaintId a.
	 */
	void removeById(SessionDto session, long complaintId);
	
	/**
	 * asd.
	 * @param complaintIds a.
	 * @param session asd.
	 */
	void removeByIds(SessionDto session, Collection<Long> complaintIds);

}
