package telephony.core.dao;

import telephony.core.entity.jpa.UserStore;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserStoresDao extends GenericDao<UserStore> {

	/**
	 * remove.
	 * @param userId as.
	 */
	void removeByUserId(Long userId);

}
