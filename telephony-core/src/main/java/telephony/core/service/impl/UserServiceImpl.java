package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.RolesDao;
import telephony.core.dao.StoresDao;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.SessionService;
import telephony.core.service.UserService;
import telephony.core.service.converter.UserConverter;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserFetchDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.*;

public class UserServiceImpl
extends AbstractBasicService<User> 
implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private UsersDao usersDao;

	@Inject
	private RolesDao rolesDao;

	@Inject
	private StoresDao storesDao;
    
    @Inject
    private SessionService sessionService;

	@Inject
	private UserConverter userConverter;

	// TODO extract to validator
	private boolean validate(UsersFetchRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getFilters())) {
			errors.add(Error.create("filters", "filters cannot be null"));
		}

		if (isNull(request.getFilters().getPage())) {
			errors.add(Error.create("filters.page", "filters.page cannot be null"));
		}

		if (isNull(request.getFilters().getPerPage())) {
			errors.add(Error.create("filters.perPage", "filters.perPage cannot be null"));
		}

		return errors.size() == 0;
	}

	@Override
	@Transactional
	public UsersFetchResponse fetch(UsersFetchRequest req)
			throws SessionServiceException {

		logger.info("UserServiceImpl.fetch starts");
		UsersFetchResponse resp = new UsersFetchResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(req,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", req.getFilters());
		}

		sessionService.validate(req.getSessionDto());

		List<UserFetchDto> userz = new ArrayList<UserFetchDto>();
		List<User> users = usersDao.find(req.getFilters());

		for (User u : users) {
			userz.add(userConverter.toFetchDto(u));
		}

		resp.setMessage("operation performed succcesfully"); // TODO add localized
		resp.setSuccess(true);
		resp.setUsers(userz);

		return resp;
	}

	// TODO extract to validator
	private boolean validate(UserEditRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getUserDto())) {
			errors.add(Error.create("userDto", "userDto cannot be null"));
			return false;
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public UserEditResponse edit(UserEditRequest request)
			throws SessionServiceException, UserServiceException {

		logger.info("UserServiceImpl.edit starts");
		UserEditResponse resp = new UserEditResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ userDto : {} ] ", request.getUserDto());
		}

		sessionService.validate(request.getSessionDto());

		User user = usersDao.findById(request.getUserDto().getId());

		userConverter.updateEntity(user, request.getUserDto());

		usersDao.saveOrUpdate(user);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO extract to validator
	private boolean validate(UserAddRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getUserDto())) {
			errors.add(Error.create("userDto", "userDto cannot be null"));
			return false;
		}

		// TODO add more cases

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public UserAddResponse add(UserAddRequest request)
			throws SessionServiceException, UserServiceException {

		logger.info("UserServiceImpl.add starts");
		UserAddResponse resp = new UserAddResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" params : [ userDto : {} ] ", request.getUserDto());
		}

		sessionService.validate(request.getSessionDto());

		User user = userConverter.toEntity(request.getUserDto());

		usersDao.save(user);

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	// TODO extract to validator
	private boolean validate(UserDeleteRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isEmpty(request.getUserId())) {
			errors.add(Error.create("userId", "userId cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public UserDeleteResponse delete(UserDeleteRequest request)
			throws SessionServiceException, UserServiceException {

		logger.info("UserServiceImpl.delete starts");
		UserDeleteResponse resp = new UserDeleteResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if(logger.isDebugEnabled()) {
			logger.debug(" params : [ userId : {} ] ", request.getUserId());
		}

		sessionService.validate(request.getSessionDto());

		usersDao.removeById(request.getUserId());

		resp.setMessage("operation performed successfully"); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}

	@Override
	@Transactional
	public long count(SessionDto session) {

		return usersDao.count();
	}

	// TODO extract to validator
	private boolean validate(UserEditRoleRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getRolesToAdd())) {
			errors.add(Error.create("rolesToAdd", "rolesToAdd cannot be null"));
		}

		if (isNull(request.getRolesToDelete())) {
			errors.add(Error.create("rolesToDelete", "rolesToDelete cannot be empty"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public UserEditRoleResponse editRoles(UserEditRoleRequest request) throws SessionServiceException {

		logger.info("UserServiceImpl.editRoles starts");
		UserEditRoleResponse resp = new UserEditRoleResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" params : [ userId: {} , rolesToAdd : {} , rolesToDelete : {} ] ",
					new Object[] { request.getUserId(), request.getRolesToAdd(), request.getRolesToDelete() } );
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		User user = usersDao.findById(request.getUserId());

		Collection<Long> rolesToAddIds = request.getRolesToAdd();
		Collection<Role> rolesToAdd = rolesDao.findByIds(rolesToAddIds);

		// TODO : move to converter
		Iterator<Role> userRoles = user.getRoles().iterator();

		while(userRoles.hasNext()) {
			Role r = userRoles.next();
			if (request.getRolesToDelete().contains(r.getId())) {
				userRoles.remove();
			}
		}

		user.getRoles().addAll(rolesToAdd);

		usersDao.saveOrUpdate(user);

		resp.setMessage("operation performed successfully");
		resp.setSuccess(true);
		return resp;
	}

	// TODO extract
	private boolean validate(UserEditStoreRequest request, List<Error> errors) {

		if (isEmpty(request.getSessionId())) {
			errors.add(Error.create("sessionId", "sessionId cannot be empty"));
		}

		if (isEmpty(request.getUsername())) {
			errors.add(Error.create("username", "username cannot be empty"));
		}

		if (isNull(request.getStoresToAdd())) {
			errors.add(Error.create("username", "storesToAdd cannot be null"));
		}

		if (isNull(request.getStoresToDelete())) {
			errors.add(Error.create("username", "storesToDelete cannot be null"));
		}

		return errors.size() == 0;
	}

	@Transactional
	@Override
	public UserEditStoreResponse editStores(UserEditStoreRequest request) throws SessionServiceException {

		logger.info("UserServiceImpl.editStores starts");
		UserEditStoreResponse resp = new UserEditStoreResponse();
		List<Error> errors = getEmptyErrors();

		if (!validate(request,errors)) {
			resp.setErrors(errors);
			resp.setMessage("validationError");
			resp.setSuccess(false);
			return resp;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" params : [ userId : {}, storesToAdd : {}, storesToRemove : {} ] ",
					new Object[] { request.getUserId(), request.getStoresToAdd(), request.getStoresToDelete() } );
		}

		sessionService.validate(request.getSessionDto());

		User user = usersDao.findById(request.getUserId());

		Collection<Long> storesToAddIds = request.getStoresToAdd();
		Collection<Store> storesToAdd = storesDao.findByIds(storesToAddIds);

		Iterator<Store> userStores = user.getAllowedShops().iterator();

		// TODO move to converter
		while(userStores.hasNext()) {
			Store r = userStores.next();
			if (request.getStoresToDelete().contains(r.getId())) {
				userStores.remove();
			}
		}

		user.getAllowedShops().addAll(storesToAdd);

		usersDao.saveOrUpdate(user);

		resp.setSuccess(true);
		resp.setMessage("operation performed successfully"); // TODO add localized msg

		return resp;
	}

}
