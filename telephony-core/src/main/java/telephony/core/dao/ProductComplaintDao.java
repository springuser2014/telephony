package telephony.core.dao;

import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.query.filter.ProductComplaintFilterCriteria;

import java.util.List;

/**
 * asd.
 */
public interface ProductComplaintDao extends GenericDao<ProductComplaint> {

	/**
	 * asd.
	 * @param complaintId a.
	 */
	void markAsInProgress(long complaintId);

	/**
	 * asd.
	 * @param complaintId a.
	 */
	void markAsAccepted(long complaintId);

	/**
	 * asd.
	 * @param complaintId a.
	 */
	void markAsRejected(long complaintId);

	/**
	 * asd.
	 * @param complaintId a.
	 */
	void markAsResolved(long complaintId);


	/**
	 * asd.
	 * @param hashUnique a.
	 * @return a.
	 */
	ProductComplaint findByHash(String hashUnique);

	/**
	 * asd.
	 * @param filters d.
	 * @return d.
	 */
	List<ProductComplaint> findByCriteria(ProductComplaintFilterCriteria filters);
}
