package telephony.core.dao;

import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.query.filter.SaleComplaintFilterCriteria;

import java.util.List;

/**
 * asd.
 */
public interface SaleComplaintDao extends GenericDao<SaleComplaint> {

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
	SaleComplaint findByHash(String hashUnique);

	/**
	 * asd.
	 * @param filters a.
	 * @return a.
	 */
	List<SaleComplaint> findByCriteria(SaleComplaintFilterCriteria filters);
}
