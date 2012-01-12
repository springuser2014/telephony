package war.server.core.dao;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDao implements IUserDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

    public UserDao() {
    }

    public List<User> findAll() {

        logger.debug("findAll stars ");

        List<User> res = em.createQuery("select u from User u").getResultList();

        return res;
    }
}

