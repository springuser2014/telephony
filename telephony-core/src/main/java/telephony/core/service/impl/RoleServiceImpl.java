package telephony.core.service.impl;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.RoleService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * Roles management service.
 */
public class RoleServiceImpl
extends AbstractBasicService<Role>
implements RoleService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private RolesDao rolesDao;
	
	@Inject
	private SessionService sessionService;
	
	@Transactional
	@Override
	public long count(Session session) {
		
		return rolesDao.count();
	}

	@Transactional
	@Override
	public List<Role> find(Session session, RoleFilterCriteria filters) 
			throws SessionServiceException {
		
		logger.debug("RoleServiceImpl.fetchAll starts");
		logger.debug("params : [ session : {} ]", session);
		
		sessionService.validate(session);
		
		return rolesDao.find(filters);		
	}

	@Transactional
	@Override
	public void add(Session session, Role newrole)
			throws SessionServiceException, RoleServiceException {
		
		logger.debug("RoleServiceImpl.add starts");
		logger.debug("params : [ session : {}, newrole : {} ]", session, newrole);
		
		sessionService.validate(session);
		
		rolesDao.save(newrole);		
	}

	@Transactional
	@Override
	public void remove(Session session, Role roleToDelete)
			throws SessionServiceException, RoleServiceException {
		
		logger.debug("RoleServiceImpl.delete starts");
		logger.debug("params : [ session : {}, roleToDelete : {}]", session, roleToDelete);
		
		sessionService.validate(session);
		
		rolesDao.remove(roleToDelete);
	}

	@Override
	@Transactional
	public Role findByLabel(Session session, String label) 
			throws SessionServiceException {
		logger.debug("RoleServiceImpl.findByLabel starts");
		logger.debug("params : [ session : {}, label : {}]", session, label);
		
		sessionService.validate(session);
		
		return rolesDao.findByLabel(label);
	}

}
