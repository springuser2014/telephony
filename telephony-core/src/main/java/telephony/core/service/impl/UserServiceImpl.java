package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.UserFilterCriteria;
import telephony.core.query.filter.UserFilterCriteriaBuilder;
import telephony.core.service.SessionService;
import telephony.core.service.UserService;
import telephony.core.service.converter.UserConverter;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.UserDto;
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
    private SessionService sessionService;

	@Override
	@Transactional
	public UsersFetchResponse fetch(UsersFetchRequest req) throws SessionServiceException {
		logger.info("UserServiceImpl.fetch starts");

		if (logger.isDebugEnabled()) {
			logger.debug("params : [ filters : {} ]", req.getFilters());
		}

		sessionService.validate(req.getSessionDto()); // TODO ; add validation

		List<UserDto> userz = new ArrayList<UserDto>();
		List<User> users = usersDao.find(req.getFilters());

		for (User u : users) {
			userz.add(UserConverter.toDto(u));
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

		UserConverter.updateEntity(user, request.getUserDto());

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

		User user = UserConverter.toEntity(request.getUserDto());

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
	public UserEditRoleResponse editRoles(UserEditRoleRequest req) {

		logger.info("UserServiceImpl.editRoles starts");

		// TODO Implement

		return null;
	}

	@Override
	public UserEditStoreResponse editStores(UserEditStoreRequest request) {
		return null;
	}

}
