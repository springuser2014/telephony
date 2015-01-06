package telephony.core.service;

import telephony.core.entity.jpa.Complaint;
import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.dto.ComplaintDto;
import telephony.core.service.dto.ComplaintEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @param <T> asd.
 */
public interface ComplaintService
		<T extends Complaint,
		 RCRes extends ReportComplaintResponse, RCReq extends ReportComplaintRequest<DTO>, DTO extends ComplaintDto,
		 CERes extends ComplaintEditResponse, CEReq extends ComplaintEditRequest<EditDTO>, EditDTO extends ComplaintEditDto,
		 CFRes extends ComplaintFetchResponse, CFReq extends ComplaintFetchRequest<CFC>, CFC extends ComplaintFilterCriteria>  extends GenericService<T> {

	// TODO add fetchDetails

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
	RCRes report(RCReq req) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 */
	CFRes fetch(CFReq req) throws SessionServiceException;
	
	/**
	 * asd.
	 * @param req a. 
	 * @return a.
	 */
	CERes editComplaint(CEReq req) throws SessionServiceException;
	
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
