package telephony.core.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.google.inject.Inject;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.service.RoleService;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * Roles management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class RoleServiceImpl
    extends AbstractBasicService<Role> implements RoleService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private RolesDao rolesDao;
	
	@Inject
	private SessionService sessionService;
	
	@Override
	public long count() {
		
		return rolesDao.count();
	}

	@Override
	public List<Role> fetchAll(String username, String sessionId) 
			throws SessionServiceException {
		
		logger.debug("RoleServiceImpl.fetchAll starts");
		logger.debug("params : [ username : {}, sessionId : {} ]", username, sessionId);
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		return rolesDao.find();		
	}

	@Override
	public void add(String username, String sessionId, Role newrole)
			throws SessionServiceException, RoleServiceException {
		
		logger.debug("RoleServiceImpl.add starts");
		logger.debug("params : [ username : {}, sessionId : {}, newrole : {} ]", username, sessionId, newrole);
		
		Session sessionToValidate = Session.create(username, sessionId);
		sessionService.validate(sessionToValidate);
		
		rolesDao.save(newrole);
		
	}

	@Override
	public void delete(String username, String sessionId, Role roleToDelete)
			throws SessionServiceException, RoleServiceException {
		
		logger.debug("RoleServiceImpl.delete starts");
		logger.debug("params : [ username : {}, sessionId : {}, roleToDelete : {}]", username, sessionId, roleToDelete);
		
		rolesDao.remove(roleToDelete);
	}

}
