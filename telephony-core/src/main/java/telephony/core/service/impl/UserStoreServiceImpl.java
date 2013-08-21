package telephony.core.service.impl;

import com.google.inject.Inject;

import telephony.core.dao.UserStoresDao;
import telephony.core.entity.jpa.UserStore;
import telephony.core.service.UserStoreService;

/**
 * UserStores management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserStoreServiceImpl 
	extends AbstractBasicService<UserStore> implements UserStoreService {

	@Inject
	private UserStoresDao userStoresDao;
	
	@Override
	public long count() {
		return userStoresDao.count();
	}
}
