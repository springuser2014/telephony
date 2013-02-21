package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;
import telephony.core.service.interfaces.UserService;

import com.google.inject.Inject;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserServiceImpl implements UserService {

    /**
     * asd.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    @Inject
    private UsersDao usersDao;

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final List<User> findAllUsers() {
        logger.debug("UserServiceImpl.findAllUsers starts");

        List<User> res = usersDao.findNotRemoved();

        logger.debug("found {} elements ", res.size());

        return res;
    }

    /**
     * asd.
     * @param name asd.
     * @return asd.
     */
    @Override
    public final User findUserByName(final String name) {
        logger.info("UserServiceImpl.findUserByName starts");
        logger.info("params : [ name = {} ] ", name);

        User u = usersDao.findByName(name);

        return u;
    }
}
