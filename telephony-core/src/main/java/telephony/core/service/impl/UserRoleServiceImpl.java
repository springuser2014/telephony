package telephony.core.service.impl;

import com.google.inject.Inject;

import telephony.core.dao.UserRolesDao;
import telephony.core.entity.jpa.UserRole;
import telephony.core.service.UserRoleService;

/**
 * UserRoles management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserRoleServiceImpl extends AbstractBasicService<UserRole> implements UserRoleService {

	@Inject
	private UserRolesDao userRolesDao;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		
		return userRolesDao.count();
	}
}
