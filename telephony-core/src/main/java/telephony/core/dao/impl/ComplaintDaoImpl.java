package telephony.core.dao.impl;

import telephony.core.dao.ComplaintDao;
import telephony.core.entity.jpa.Complaint;

public class ComplaintDaoImpl
extends GenericDaoImpl<Complaint>
implements ComplaintDao {

	public ComplaintDaoImpl() {
		super(Complaint.class);
	}

	@Override
	public Complaint findByHash(String hashUnique) {
		
		return 
			(Complaint) getEntityManager()
				.createQuery("select from Complaint p where p.uniqueHash = :hash")
				.setParameter("hash", hashUnique)
				.getSingleResult();
	}

}
