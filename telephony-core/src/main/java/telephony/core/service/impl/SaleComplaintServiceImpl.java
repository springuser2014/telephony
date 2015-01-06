package telephony.core.service.impl;

import com.google.inject.persist.Transactional;

import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.query.filter.SaleComplaintFilterCriteria;
import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public class SaleComplaintServiceImpl 
extends AbstractGenericService<SaleComplaint, SaleComplaintDao>
implements SaleComplaintService {

	/**
	 * asd.
	 */
	public SaleComplaintServiceImpl() {
		super();
	}

	/**
	 * asd.
	 * @param clazz a.
	 */
	public SaleComplaintServiceImpl(Class<SaleComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public SaleComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

	// TODO : make this method generic
	@Override
	public ReportSaleComplaintResponse report(ReportSaleComplaintRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest saleComplaintFetchRequest) throws SessionServiceException {
		return null;
	}

	// TODO : make this method generic
	@Override
	public SaleComplaintEditResponse editComplaint(SaleComplaintEditRequest req) {
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
