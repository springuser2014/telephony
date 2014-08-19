package telephony.core.service.impl;

import java.util.Collection;

import javax.persistence.EntityManager;

import com.google.inject.persist.Transactional;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;
import telephony.core.service.bean.Session;


/**
 * asd.
 * @param <T> a.
 * @param <D> asd .
 */
public class AbstractComplaintService<T extends Complaint, D extends GenericDao<T>> 
implements ComplaintService<T> {

	private D complaintDao;
	
	/**
	 * asd.
	 * @return a.
	 */
	protected D getComplaintDao() {
		return complaintDao;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	@Override
	public void report(Session session, T complaint) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public Complaint update(Session session, T complaint) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public Collection<T> update(Session session, Collection<T> complaints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void markAsInProgress(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsAccepted(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsRejected(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsResolved(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void removeById(Session session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void removeByIds(Session session, Collection<Long> complaintIds) {
		// TODO Auto-generated method stub
		
	}

}
