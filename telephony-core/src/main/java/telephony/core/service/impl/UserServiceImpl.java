package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.service.UserService;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.UserServiceException;

import com.google.inject.Inject;


/**
 * Implementation of basic UserService functionalities.
 */
public class UserServiceImpl extends AbstractBasicService implements UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private UsersDao usersDao;

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<User> findAllUsers(String username, String sessionId) {
        logger.debug("UserServiceImpl.findAllUsers starts");

        List<User> res = usersDao.find();

        logger.debug("found {} elements ", res.size());

        return res;
    } 

	@Override
	public List<User> findUsersByStoreId(String username, String sessionId,
			Long storeId) throws SessionServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserById(String username, String sessionId, Long userId)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(String username, String sessionId, User user)
			throws SessionServiceException, UserServiceException {
		// TODO Auto-generated method stub
		
	}
}
