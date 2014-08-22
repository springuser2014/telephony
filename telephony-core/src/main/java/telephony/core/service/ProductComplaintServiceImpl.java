package telephony.core.service;

import java.util.Collection;

import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.bean.Session;
import telephony.core.service.impl.AbstractGenericService;

import com.google.inject.persist.Transactional;

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


	@Transactional
	@Override
	public void report(Session session, ProductComplaint complaint) {
		
		// TODO session's validation
		
		dao().save(complaint);		
	}

	@Transactional
	@Override
	public Complaint update(Session session, ProductComplaint complaint) {
		
		// TODO session's validation
		
		return dao().saveOrUpdate(complaint);
	}

	@Transactional
	@Override
	public Collection<ProductComplaint> update(Session session, Collection<ProductComplaint> complaints) {
		
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
	public ProductComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}
	
}
