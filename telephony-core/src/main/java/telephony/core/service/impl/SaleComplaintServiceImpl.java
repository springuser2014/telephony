package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public class SaleComplaintServiceImpl 
extends AbstractBasicService<SaleComplaint> implements SaleComplaintService {

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void report(Session session, SaleComplaint complaint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint update(Session session, SaleComplaint complaint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<SaleComplaint> update(Session session, Collection<SaleComplaint> complaints) {
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
