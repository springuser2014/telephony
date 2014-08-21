package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.service.bean.Session;

/**
 * asd.
 * @param <T> asd.
 */
public interface ComplaintService<T extends Complaint>  extends GenericService<T>  {
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 */
	void report(Session session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaint a.
	 * @return a.
	 */
	Complaint update(Session session, T complaint);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaints a.
	 * @return a.
	 */
	Collection<T> update(Session session, Collection<T> complaints);
	
	/**
	 * asd.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsInProgress(Session session, long complaintId);
	
	/**
	 * ads.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsAccepted(Session session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsRejected(Session session, long complaintId);
	
	/**
	 * ad.
	 * @param session a.
	 * @param complaintId a.
	 */
	void markAsResolved(Session session, long complaintId);
	
	/**
	 * asd.
	 * @param session asd.
	 * @param complaintId a.
	 */
	void removeById(Session session, long complaintId);
	
	/**
	 * asd.
	 * @param complaintIds a.
	 * @param session asd.
	 */
	void removeByIds(Session session, Collection<Long> complaintIds);

}
