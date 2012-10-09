package telephony.server.core.service.implementations;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.core.dao.interfaces.UsersDao;
import telephony.server.core.entity.User;
import telephony.server.core.service.interfaces.UserService;

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
}
