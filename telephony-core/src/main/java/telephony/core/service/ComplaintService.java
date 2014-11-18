package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.ComplaintChangeStatusResponseDto;
import telephony.core.service.dto.response.ComplaintEditResponseDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.response.ComplaintFetchResponseDto;
import telephony.core.service.dto.response.ReportComplaintResponseDto;

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
	ReportComplaintResponseDto report(ReportComplaintRequestDto req);
	
	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 */
	ComplaintFetchResponseDto fetch(ComplaintFetchRequestDto req);
	
	/**
	 * asd.
	 * @param req a. 
	 * @return a.
	 */
	ComplaintEditResponseDto editComplaint(ComplaintEditRequestDto req);
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponseDto markAsRejected(ComplaintChangeStatusRequestDto req);
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponseDto markAsInProgress(ComplaintChangeStatusRequestDto req);
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponseDto markAsAccepted(ComplaintChangeStatusRequestDto req);
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponseDto markAsResolved(ComplaintChangeStatusRequestDto req);
	
	/**
	 * asd.
	 * @param req a.
	 */
	void deleteComplaint(DeleteComplaintRequestDto req);
	
	// TODO delete the stuff below
	
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
