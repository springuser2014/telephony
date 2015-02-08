package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.RolesDao;
import telephony.core.entity.jpa.Role;
import telephony.core.service.RoleService;
import telephony.core.service.SessionManager;
import telephony.core.service.converter.RoleConverter;
import telephony.core.service.dto.RoleDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.dto.request.RoleDeleteRequest;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.Error;
import telephony.core.service.dto.response.RoleAddResponse;
import telephony.core.service.dto.response.RoleDeleteResponse;
import telephony.core.service.dto.response.RoleFetchResponse;
import telephony.core.service.exception.RoleServiceException;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.*;

public class RoleServiceImpl
extends AbstractBasicService<Role>
implements RoleService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	RolesDao rolesDao;
	
	@Inject
	SessionManager sessionManager;

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

		RoleFetchResponse resp = new RoleFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);

			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", request.getFilters());
		}

		sessionManager.validate(request.getSessionDto());

		List<RoleDto> rolez = new ArrayList<RoleDto>();
		List<Role> roles = rolesDao.find(request.getFilters());

		for (Role r : roles) {
			rolez.add(roleConverter.toDto(r));
		}

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		resp.setRoles(rolez);

		return resp;
	}

	@Transactional
	@Override
	public RoleAddResponse add(RoleAddRequest request)
			throws SessionServiceException, RoleServiceException {

		logger.info("RoleServiceImpl.add starts");
		RoleAddResponse resp = new RoleAddResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {

			resp.setErrors(errors);
			resp.setSuccess(false);
			resp.setMessage("validationError");
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ roleDto : {}] ", request.getRoleDto());
		}

		sessionManager.validate(request.getSessionDto()); // TODO add validation

		Role entity = roleConverter.toEntity(request.getRoleDto());

		rolesDao.save(entity);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Transactional
	@Override
	public RoleDeleteResponse delete(RoleDeleteRequest request)
			throws SessionServiceException, RoleServiceException {

		logger.info("RoleServiceImpl.delete starts");

		RoleDeleteResponse resp = new RoleDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {

			resp.setMessage("validationError");
			resp.setSuccess(false);
			resp.setErrors(errors);

			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ roleId : {} ]", request.getRoleId());
		}

		rolesDao.removeById(request.getRoleId());

		resp.setMessage("operation performed succesfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(RoleFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be empty"));
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if (isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(RoleDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getRoleId())) {
			errors.add(Error.create("roleId", "roleId cannot be empty"));
		}

		return errors.size() == 0;
	}

	// TODO extract to validator
	private boolean validate(RoleAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getRoleDto())) {
			errors.add(Error.create("roleDto", "roleDto cannot be null"));
			return false;
		}

		if (isEmpty(request.getRoleDto().getLabel())) {
			errors.add(Error.create("roleDto.label", "roleDto.label cannot be empty"));
		}

		return errors.size() == 0;
	}
}
