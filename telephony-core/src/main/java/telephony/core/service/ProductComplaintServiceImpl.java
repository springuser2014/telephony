package telephony.core.service;

import java.util.Collection;

import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.bean.Session;
import telephony.core.service.impl.AbstractBasicService;
import telephony.core.service.impl.AbstractComplaintService;
import telephony.core.service.impl.AbstractGenericService;

/**
 * asd.
 */
public class ProductComplaintServiceImpl 
extends AbstractGenericService<ProductComplaint, ProductComplaintDao> 
implements ProductComplaintService {
	
	/**
	 * asd.
	 */
	public ProductComplaintServiceImpl() {
		super();
	}

	/**
	 * sad.
	 * @param clazz a.
	 */
	public ProductComplaintServiceImpl(Class<ProductComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void report(Session session, ProductComplaint complaint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint update(Session session, ProductComplaint complaint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ProductComplaint> update(Session session,
			Collection<ProductComplaint> complaints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markAsInProgress(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsAccepted(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsRejected(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsResolved(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeByIds(Session session, Collection<Long> complaintIds) {
		// TODO Auto-generated method stub
		
	}
	
}
