package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoreRolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.StoreRole;

/**
 * 
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoreRolesDaoImpl extends GenericDaoImpl<StoreRole> 
	implements StoreRolesDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public StoreRolesDaoImpl() {
		super(StoreRole.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeStoreRolesByStore(Store store) {
		logger.debug("removeStoreRoles starts");
		logger.debug("params : [ store: {}]", store);
		
		getEntityManager()
		.createQuery("delete from StoreRole e where e.store = ?1")
		.setParameter(1, store)
		.executeUpdate();	
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreRole> findByStore(Store storeToDelete) {
		
		logger.debug("findByStore starts");
		logger.debug("params : [ store : {}]", storeToDelete);
		
		List<StoreRole> lst = (List<StoreRole>) getEntityManager()
		.createQuery("select e from StoreRole e where e.store = ?1")
		.setParameter(1, storeToDelete)
		.getResultList();
		
		logger.debug("found {} elements", lst.size());
		
		return lst;
	}
	
	

}
