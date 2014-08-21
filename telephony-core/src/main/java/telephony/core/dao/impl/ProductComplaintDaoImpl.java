package telephony.core.dao.impl;

import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.ProductComplaint;

/**
 * asd.
 */
public class ProductComplaintDaoImpl 
extends GenericDaoImpl<ProductComplaint> implements ProductComplaintDao {

	/**
	 * ads.
	 */
	public ProductComplaintDaoImpl() {
		super(ProductComplaint.class);
	}
	

	@Override
	public void markAsInProgress(long complaintId) {
		
		getEntityManager()
			.createQuery("update ProductComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.IN_PROGRESS)
			.executeUpdate();
	}

	@Override
	public void markAsAccepted(long complaintId) {
	
		getEntityManager()
			.createQuery("update ProductComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.ACCEPTED)
			.executeUpdate();
	}

	@Override
	public void markAsRejected(long complaintId) {
	
		getEntityManager()
			.createQuery("update ProductComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.REJECTED)
			.executeUpdate();
	}

	@Override
	public void markAsResolved(long complaintId) {

		getEntityManager()
			.createQuery("update ProductComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.RESOLVED)
			.executeUpdate();
	}


}
