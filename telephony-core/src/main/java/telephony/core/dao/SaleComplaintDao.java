package telephony.core.dao;

import telephony.core.entity.jpa.SaleComplaint;

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
}
