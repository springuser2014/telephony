package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UserStoresDao;
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

	@Override
	public void removeByUserId(Long userId) {
		
		logger.info("removeByUserId starts");
		
		getEntityManager()
		.createQuery("delete from UserStore e where e.userId = ?1")
		.setParameter(1, userId)
		.executeUpdate();
	}
}
