package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.SessionDto;


/**
 * RCRes extends ReportComplaintResponse, RCReq extends ReportComplaintRequest,
 CFRes extends ComplaintFetchResponse, CFReq extends ComplaintFetchRequest
 * asd.
 * @param <T> a.
 * @param <D> asd .
 */
public class AbstractComplaintService<T extends Complaint, D extends GenericDao<T>>
extends AbstractGenericService<T, D>
implements ComplaintService<T> {

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
	public T findByHash(String hashUnique) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReportComplaintResponse report(ReportComplaintRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintFetchResponse fetch(ComplaintFetchRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductComplaintEditResponse editComplaint(ProductComplaintEditRequest req) {
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

