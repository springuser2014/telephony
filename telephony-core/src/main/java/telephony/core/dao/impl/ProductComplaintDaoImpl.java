package telephony.core.dao.impl;

import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.query.filter.ProductComplaintFilterCriteria;

import javax.persistence.Query;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;
/**
 * asd.
 */
public class ProductComplaintDaoImpl 
extends GenericDaoImpl<ProductComplaint> 
implements ProductComplaintDao {

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
	
	@SuppressWarnings("unchecked")
	@Override
	public ProductComplaint findByHash(String hashUnique) {
		
		List<ProductComplaint> list = 
				(List<ProductComplaint>) getEntityManager()
				.createQuery("select p from ProductComplaint p where p.uniqueHash like :hash")
				.setParameter("hash", "%" +hashUnique + "%")
				.getResultList();
		
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ProductComplaint> findByCriteria(ProductComplaintFilterCriteria filters) {

		StringBuilder sb = new StringBuilder();
		sb.append(" select c from ProductComplaint c join c.product p where 1=1 ");

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

		if (isNotEmpty(filters.getProductsIds())) {
			sb.append(" and p.id in (:productsIds) ");
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

		if (isNotEmpty(filters.getProductsIds())) {
			q.setParameter("productsIds", filters.getProductsIds());
		}


		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		List<ProductComplaint> complaints = q.getResultList();
		return complaints;
	}

}
