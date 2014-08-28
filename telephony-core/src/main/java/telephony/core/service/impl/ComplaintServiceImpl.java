package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.Session;

/**
 * asd.
 */
public abstract class ComplaintServiceImpl 
extends AbstractGenericService<Complaint, ComplaintDao> 
implements ComplaintService<Complaint> {

	/**
	 * asd.
	 * @param clazz a.
	 */
	public ComplaintServiceImpl(Class<ComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Complaint findByHash(String hashUnique) {
		
		return dao().findByHash(hashUnique);
	}


	@Transactional
	@Override
	public long count(Session session) {

		return dao().count();
	}

	@Override
	public void report(Session session, Complaint complaint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint update(Session session, Complaint complaint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection update(Session session, Collection complaints) {
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
	public void removeByIds(Session session, Collection complaintIds) {
		// TODO Auto-generated method stub
		
	}

}
