package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.bean.Session;

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
	public void report(Session session, SaleComplaint complaint) {
		
		// TODO session's validation
		
		dao().save(complaint);		
	}

	@Transactional
	@Override
	public Complaint update(Session session, SaleComplaint complaint) {
		
		// TODO session's validation
		
		return dao().saveOrUpdate(complaint);
	}

	@Transactional
	@Override
	public Collection<SaleComplaint> update(Session session, Collection<SaleComplaint> complaints) {
		
		// TODO session's validation
		
		return dao().saveOrUpdate(complaints);
	}

	@Transactional
	@Override
	public void markAsInProgress(Session session, long complaintId) {

		// TODO session's validation
		
		dao().markAsInProgress(complaintId);
	}


	@Transactional
	@Override
	public void markAsAccepted(Session session, long complaintId) {
	
		// TODO session's validation

		dao().markAsAccepted(complaintId);
	}

	@Transactional
	@Override
	public void markAsRejected(Session session, long complaintId) {

		// TODO session's validation

		dao().markAsRejected(complaintId);
	}

	@Transactional
	@Override
	public void markAsResolved(Session session, long complaintId) {

		// TODO session's validation

		dao().markAsResolved(complaintId);		
	}

	@Transactional
	@Override
	public void removeById(Session session, long complaintId) {
		
		// TODO session's validation
		
		dao().removeById(complaintId);		
	}

	@Transactional
	@Override
	public void removeByIds(Session session, Collection<Long> complaintIds) {

		// TODO session's validation
		
		dao().removeByIds(complaintIds);
	}

	@Transactional
	@Override
	public SaleComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

}
