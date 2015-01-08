package telephony.core.service.impl;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.DetailedComplaintDto;
import telephony.core.service.dto.ComplaintDto;
import telephony.core.service.dto.ComplaintEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.ComplaintService;
import telephony.core.service.exception.SessionServiceException;


/**
 * RCRes extends ReportComplaintResponse, RCReq extends ReportComplaintRequest,
 CFRes extends ComplaintFetchResponse, CFReq extends ComplaintFetchRequest
 * asd.
 * @param <T> a.
 * @param <D> asd .
 */
public class AbstractComplaintService<T extends Complaint, D extends GenericDao<T>,
		FDRes extends ComplaintDetailsFetchResponse<DDTO>, FDReq extends ComplaintDetailsFetchRequest, DDTO extends DetailedComplaintDto,
		RCRes extends ReportComplaintResponse, RCReq extends ReportComplaintRequest<DTO>, DTO extends ComplaintDto,
		CERes extends ComplaintEditResponse, CEReq extends ComplaintEditRequest<EditDTO>, EditDTO extends ComplaintEditDto,
		CFRes extends ComplaintFetchResponse, CFReq extends ComplaintFetchRequest<CFC>, CFC extends ComplaintFilterCriteria>
extends AbstractGenericService<T, D>
implements ComplaintService
		<T,
		FDRes, FDReq, DDTO,
		RCRes, RCReq, DTO,
		CERes, CEReq, EditDTO,
		CFRes, CFReq, CFC> {

	public AbstractComplaintService(Class<D> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	private D complaintDao;
	
	/**
	 * asd.
	 * @return a.
	 */
	protected D getComplaintDao() {
		return complaintDao;
	}

	@Override
	public FDRes fetchDetails(FDReq req) {
		return null;
	}

	@Override
	public T findByHash(String hashUnique) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CFRes fetch(CFReq req) throws SessionServiceException {
		return null;
	}

	@Override
	public CERes editComplaint(CEReq req) throws SessionServiceException {
		return null;
	}

	@Override
	public RCRes report(RCReq req) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ComplaintChangeStatusResponse markAsRejected(ComplaintChangeStatusRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponse markAsInProgress(ComplaintChangeStatusRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponse markAsAccepted(ComplaintChangeStatusRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintDeleteResponse deleteComplaint(ComplaintDeleteRequest req) {
		// TODO Auto-generated method stub

		return null;
	}
}

