package war.server.core.dao.implementations;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.UserDao;
import war.server.core.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

    public UserDaoImpl() {
    }

    public List<User> findAll() {

        logger.debug("findAll stars ");

        List<User> res = em.createQuery("select u from User u").getResultList();

        return res;
    }

    public User find(Long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<User> find(List<Long> ids) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete(Long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete(List<Long> ids) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void save(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void save(List<User> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void saveOrUpdate(User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void saveOrUpdate(List<User> users) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

