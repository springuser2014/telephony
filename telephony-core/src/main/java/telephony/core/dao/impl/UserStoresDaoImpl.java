package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserStoresDao;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.UserStore;

/**
 * UserStores management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserStoresDaoImpl extends GenericDaoImpl<UserStore> implements  UserStoresDao {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public UserStoresDaoImpl() {
        super(UserStore.class);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public void removeByUserId(Long userId) {
		
		logger.info("removeByUserId starts");
		
		getEntityManager()
		.createQuery("delete from UserStore e where e.user = ?1")
		.setParameter(1, userId)
		.executeUpdate();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserStore> findByStore(Store storeToDelete) {
		
		logger.info("findByStore starts");
		
		List<UserStore> res = (List<UserStore>) getEntityManager()
		.createQuery("select e from UserStore e where e.store = ?1")
		.setParameter(1, storeToDelete)
		.getResultList();
		
		logger.info("found {} elements", res.size());
		
		return res;
	}
}
