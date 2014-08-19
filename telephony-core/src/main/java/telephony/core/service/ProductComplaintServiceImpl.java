package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.bean.Session;
import telephony.core.service.impl.AbstractBasicService;

/**
 * asd.
 */
public class ProductComplaintServiceImpl 
extends AbstractBasicService<ProductComplaint> implements ProductComplaintService {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
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
