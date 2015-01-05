package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @param <T> asd.
 */
public interface ComplaintService
		<T extends Complaint>  extends GenericService<T> {
	
	/**
	 * asd.
	 * @param hashUnique ad.
	 * @return asd.
	 */
	@Deprecated
	T findByHash(String hashUnique);
	
	/**
	 * asd.
	 * @param req a. 
	 * @return a.
	 */
	ReportComplaintResponse report(ReportComplaintRequest req) throws SessionServiceException;
	
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
	ProductComplaintEditResponse editComplaint(ProductComplaintEditRequest req) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest req) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest req) throws SessionServiceException;
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest req) throws SessionServiceException;
	

	/**
	 * asd.
	 * @param req ad.
	 * @return d.
	 */
	ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest req) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param req a.
	 */
	ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest req) throws SessionServiceException;
}
