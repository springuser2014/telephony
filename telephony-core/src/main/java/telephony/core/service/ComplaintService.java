package telephony.core.service;

import telephony.core.entity.jpa.Complaint;
import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.dto.ComplaintDto;
import telephony.core.service.dto.ComplaintEditDto;
import telephony.core.service.dto.DetailedComplaintDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;

public interface ComplaintService
		<T extends Complaint,
		 FDRes extends ComplaintDetailsFetchResponse<DDTO>, FDReq extends ComplaintDetailsFetchRequest, DDTO extends DetailedComplaintDto,
		 RCRes extends ReportComplaintResponse, RCReq extends ReportComplaintRequest<DTO>, DTO extends ComplaintDto,
		 CERes extends ComplaintEditResponse, CEReq extends ComplaintEditRequest<EditDTO>, EditDTO extends ComplaintEditDto,
		 CFRes extends ComplaintFetchResponse, CFReq extends ComplaintFetchRequest<CFC>, CFC extends ComplaintFilterCriteria>  extends GenericService<T> {

	FDRes fetchDetails(FDReq req) throws SessionServiceException;

	@Deprecated
	T findByHash(String hashUnique);
	
	RCRes report(RCReq req) throws SessionServiceException;

	CFRes fetch(CFReq req) throws SessionServiceException;

	CERes editComplaint(CEReq req) throws SessionServiceException;
	

	ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest req)
			throws SessionServiceException;

	ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest req)
			throws SessionServiceException;

	ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest req)
			throws SessionServiceException;

	ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest req)
			throws SessionServiceException;

	ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest req)
			throws SessionServiceException;
}
