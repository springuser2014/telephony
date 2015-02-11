package telephony.core.dao;

import telephony.core.entity.jpa.Complaint;

public interface ComplaintDao extends GenericDao<Complaint> {

	Complaint findByHash(String hashUnique);

}
