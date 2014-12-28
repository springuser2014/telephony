package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.query.filter.RoleFilterCriteria;
import telephony.core.service.converter.RoleConverter;
import telephony.core.service.converter.UserConverter;
import telephony.core.service.dto.RoleDto;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.dto.request.RoleDeleteRequest;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.RoleAddResponse;
import telephony.core.service.dto.response.RoleDeleteResponse;
import telephony.core.service.dto.response.RoleFetchResponse;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.RoleService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.SessionDto;

/**
 * Roles management service.
 */
public class RoleServiceImpl
extends AbstractBasicService<Role>
implements RoleService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	RolesDao rolesDao;
	
	@Inject
	SessionService sessionService;

	@Inject
	RoleConverter roleConverter;

	@Transactional
	@Override
	public long count(SessionDto session) {
		
		return rolesDao.count();
	}

	@Transactional
	@Override
	public RoleFetchResponse fetch(RoleFetchRequest request)
			throws SessionServiceException, RoleServiceException {

		logger.info("RoleServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		List<RoleDto> rolez = new ArrayList<RoleDto>();
		List<Role> roles = rolesDao.find(request.getFilters());

		for (Role r : roles) {
			rolez.add(roleConverter.toDto(r));
		}

		RoleFetchResponse resp = new RoleFetchResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		resp.setRoles(rolez);

		return resp;
	}

	@Transactional
	@Override
	public RoleAddResponse add(RoleAddRequest request)
			throws SessionServiceException, RoleServiceException {

		logger.info("RoleServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ roleDto : {}] ", request.getRoleDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		Role entity = roleConverter.toEntity(request.getRoleDto());

		rolesDao.save(entity);

		RoleAddResponse resp = new RoleAddResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public RoleDeleteResponse delete(RoleDeleteRequest request)
			throws SessionServiceException, RoleServiceException {

		logger.info("RoleServiceImpl.delete starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ roleId : {} ]", request.getRoleId());
		}

		rolesDao.removeById(request.getRoleId());

		RoleDeleteResponse resp = new RoleDeleteResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

}
