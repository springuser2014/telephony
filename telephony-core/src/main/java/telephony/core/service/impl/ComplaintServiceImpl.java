package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.SessionDto;

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
	public long count(SessionDto session) {

		return dao().count();
	}

	@Override
	public void report(SessionDto session, Complaint complaint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Complaint update(SessionDto session, Complaint complaint) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection update(SessionDto session, Collection complaints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markAsInProgress(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsAccepted(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsRejected(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markAsResolved(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeByIds(SessionDto session, Collection complaintIds) {
		// TODO Auto-generated method stub
		
	}

}
