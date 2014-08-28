package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.Session;


/**
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

	@Override
	public T findByHash(String hashUnique) {
		// TODO Auto-generated method stub
		return null;
	}
}

