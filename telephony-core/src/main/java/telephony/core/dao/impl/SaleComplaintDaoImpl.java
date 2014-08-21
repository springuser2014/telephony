package telephony.core.dao.impl;

import java.util.List;

import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.SaleComplaint;


/**
 * asd.
 */
public class SaleComplaintDaoImpl 
extends GenericDaoImpl<SaleComplaint> 
implements SaleComplaintDao {

	/**
	 * asd.
	 */
	public SaleComplaintDaoImpl() {
		super(SaleComplaint.class);
	}

	@Override
	public void markAsInProgress(long complaintId) {
		
		getEntityManager()
			.createQuery("update SaleComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.IN_PROGRESS)
			.executeUpdate();
	}

	@Override
	public void markAsAccepted(long complaintId) {
	
		getEntityManager()
			.createQuery("update SaleComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.ACCEPTED)
			.executeUpdate();
	}

	@Override
	public void markAsRejected(long complaintId) {
	
		getEntityManager()
			.createQuery("update SaleComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.REJECTED)
			.executeUpdate();
	}

	@Override
	public void markAsResolved(long complaintId) {

		getEntityManager()
			.createQuery("update SaleComplaint sc set sc.status = :status")
			.setParameter("status", ComplaintStatus.RESOLVED)
			.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SaleComplaint findByHash(String hashUnique) {
		
		List<SaleComplaint> list =  
			(List<SaleComplaint>) getEntityManager()
				.createQuery("select p from SaleComplaint p where p.uniqueHash like :hash")
				.setParameter("hash", "%" + hashUnique + "%")
				.getResultList();
		
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
