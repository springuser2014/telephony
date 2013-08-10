package telephony.core.service.impl;

import com.google.inject.Inject;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.service.RoleService;

/**
 * Roles management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RoleServiceImpl
    extends AbstractBasicService<Role> implements RoleService {

	@Inject
	private RolesDao rolesDao;
	
	@Override
	public long count() {
		
		return rolesDao.count();
	}

}
