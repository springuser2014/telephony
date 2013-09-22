package telephony.core.dao;

import java.util.List;
import java.util.Set;

import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
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

	/**
	 * asd.
	 * @param storeToDelete asd.
	 * @return asd.
	 */
	List<UserStore> findByStore(Store storeToDelete);

	/**
	 * asd.
	 * @param user asd.
	 * @param storeToDelete asd.
	 * @return asd.
	 */
	List<UserStore> findByUserAndStore(User user, Set<Store> storeToDelete);

}
