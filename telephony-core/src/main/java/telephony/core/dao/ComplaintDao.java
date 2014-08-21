package telephony.core.dao;

import telephony.core.entity.jpa.Complaint;

/**
 * asd.
 */
public interface ComplaintDao extends GenericDao<Complaint> {

	/**
	 * asd.
	 * @param hashUnique a.
	 * @return a.
	 */
	Complaint findByHash(String hashUnique);

}
