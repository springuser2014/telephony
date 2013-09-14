package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoreRolesDao;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.StoreRole;

public class StoreRolesDaoImpl extends GenericDaoImpl<StoreRole> 
	implements StoreRolesDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public StoreRolesDaoImpl() {
		super(StoreRole.class);
	}

	@Override
	public void removeStoreRolesByStore(Store store) {
		logger.debug("removeStoreRoles starts");
		logger.debug("params : [ store: {}]", store);
		
		getEntityManager()
		.createQuery("delete from StoreRole e where e.store = ?1")
		.setParameter(1, store)
		.executeUpdate();	
	}

}
