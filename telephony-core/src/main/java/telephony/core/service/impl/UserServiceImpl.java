package telephony.core.service.impl;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;
import telephony.core.service.interfaces.UserService;

import java.util.List;


public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private UsersDao usersDao;

    public List<User> findAllUsers() {
        logger.debug("UserServiceImpl.findAllUsers starts");

        List<User> res = usersDao.findUndeleted();

        logger.debug("found {} elements ", res.size());

        return res;
    }

    @Override
    public User findUserByName(String name) {
        logger.info("UserServiceImpl.findUserByName starts");
        logger.info("params : [ name = {} ] ", name);

        User u = usersDao.findByName(name);

        return u;
    }
}
