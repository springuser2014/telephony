package telephony.core.service.impl;

import java.util.*;

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
import telephony.core.service.dto.UserDto;
import telephony.core.service.dto.UserFetchDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Users management service.
 */
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

	@Override
	@Transactional
	public UsersFetchResponse fetch(UsersFetchRequest req) throws SessionServiceException {
		logger.info("UserServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", req.getFilters());
		}

		sessionService.validate(req.getSessionDto()); // TODO ; add validation

		List<UserFetchDto> userz = new ArrayList<UserFetchDto>();
		List<User> users = usersDao.find(req.getFilters());

		for (User u : users) {
			userz.add(userConverter.toFetchDto(u));
		}

		UsersFetchResponse resp = new UsersFetchResponse();
		resp.setMessage(""); // TODO add localized
		resp.setSuccess(true);
		resp.setUsers(userz);

		return resp;
	}

	@Transactional
	@Override
	public UserEditResponse edit(UserEditRequest request)
			throws SessionServiceException, UserServiceException {
		logger.info("UserServiceImpl.edit starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ userDto : {} ] ", request.getUserDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO : add validation

		User user = usersDao.findById(request.getUserDto().getId());

		userConverter.updateEntity(user, request.getUserDto());

		usersDao.saveOrUpdate(user);

		UserEditResponse resp = new UserEditResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	@Transactional
	@Override
	public UserAddResponse add(UserAddRequest request)
			throws SessionServiceException, UserServiceException {

		logger.info("UserServiceImpl.add starts");

		if (logger.isDebugEnabled()) {
			logger.debug(" params : [ userDto : {} ] ", request.getUserDto());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		User user = userConverter.toEntity(request.getUserDto());

		usersDao.save(user);

		UserAddResponse resp = new UserAddResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);
		return resp;
	}

	@Transactional
	@Override
	public UserDeleteResponse delete(UserDeleteRequest request)
			throws SessionServiceException, UserServiceException {

		logger.info("UserServiceImpl.delete starts");

		if(logger.isDebugEnabled()) {
			logger.debug(" params : [ userId : {} ] ", request.getUserId());
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

		usersDao.removeById(request.getUserId());

		UserDeleteResponse  resp = new UserDeleteResponse();
		resp.setMessage(""); // TODO add localized msg
		resp.setSuccess(true);

		return resp;
	}


	@Override
	@Transactional
	public long count(SessionDto session) {

		return usersDao.count();
	}

	@Transactional
	@Override
	public UserEditRoleResponse editRoles(UserEditRoleRequest request) throws SessionServiceException {

		logger.info("UserServiceImpl.editRoles starts");

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

		UserEditRoleResponse resp = new UserEditRoleResponse();
		resp.setMessage(""); // TODO add validation
		resp.setSuccess(true);
		return resp;
	}

	@Transactional
	@Override
	public UserEditStoreResponse editStores(UserEditStoreRequest request) throws SessionServiceException {

		logger.info("UserServiceImpl.editStores starts");

		if (logger.isDebugEnabled()) {
			logger.debug(" params : [ userId : {}, storesToAdd : {}, storesToRemove : {} ] ",
					new Object[] { request.getUserId(), request.getStoresToAdd(), request.getStoresToDelete() } );
		}

		sessionService.validate(request.getSessionDto()); // TODO add validation

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

		UserEditStoreResponse resp = new UserEditStoreResponse();
		resp.setSuccess(true);
		resp.setMessage(""); // TODO add localized msg

		return resp;
	}

}
