package telephony.core.dao.impl;

import java.util.List;

import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.query.filter.SaleComplaintFilterCriteria;

import javax.persistence.Query;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;

public class SaleComplaintDaoImpl
extends GenericDaoImpl<SaleComplaint> 
implements SaleComplaintDao {

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

	@Override
	public List<SaleComplaint> findByCriteria(SaleComplaintFilterCriteria filters) {

		StringBuilder sb = new StringBuilder();
		sb.append(" select c from SaleComplaint c join c.sale s where 1=1 ");

		// TODO add filtering by contact

		if (isNotEmpty(filters.getDescription())) {
			sb.append(" and c.description = :description ");
		}

		if (isNotNull(filters.getReportedDateFrom())) {
			sb.append(" and c.reportedDate >= :reportedDateFrom ");
		}

		if (isNotNull(filters.getReportedDateTo())) {
			sb.append(" and c.reportedDate <= :reportedDateTo ");
		}

		if (isNotNull(filters.getTitle())) {
			sb.append(" and c.title = :title ");
		}

		if (isNotEmpty(filters.getSalesIds())) {
			sb.append(" and s.id in (:salesIds) ");
		}

		Query q = getEntityManager().createQuery(sb.toString());

		if (isNotEmpty(filters.getDescription())) {
			q.setParameter("description", filters.getDescription());
		}

		if (isNotNull(filters.getReportedDateFrom())) {
			q.setParameter("reportedDateFrom", filters.getReportedDateFrom());
		}

		if (isNotNull(filters.getReportedDateTo())) {
			q.setParameter("reportedDateTo", filters.getReportedDateTo());
		}

		if (isNotNull(filters.getTitle())) {
			q.setParameter("title", filters.getTitle());
		}

		if (isNotEmpty(filters.getSalesIds())) {
			q.setParameter("salesIds", filters.getSalesIds());
		}

		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage()) * filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		List<SaleComplaint> complaints = q.getResultList();
		return complaints;
	}
}
