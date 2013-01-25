package telephony.core.dao.implementations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;

import java.util.Date;
import java.util.List;

public class UsersDaoImpl extends GenericDaoImpl<User> implements UsersDao {

    public UsersDaoImpl() {
        super(User.class);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<User> findUndeleted() {

        logger.debug("findUndeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<User> lst = getEntityManager()
                .createQuery("select e from User e where e.deleter is null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    @Override
    public User findByName(String name) {

        logger.info("findByName starts ");
        logger.info("params : [ name = {} ]", name);

        User u = (User) getEntityManager()
                .createQuery("select e from User e where e.email = ?1")
                .setParameter(1, name)
                .getSingleResult();

        logger.info("found {} element", u);

        return u;
    }

    @Override
    public User findByNameAndPassword(String name, String password) {
        logger.info("findByNameAndPassword starts");
        logger.info("params : [ name = {} , password = {} ]", name, password);

        User u = (User) getEntityManager()
                .createQuery("select e from User e where e.email = ?1 and e.password = ?2")
                .setParameter(1, name)
                .setParameter(2, name)
                .getSingleResult();

        logger.info("found {} element", u);

        return u;
    }

    @Override
    public boolean updateSession(Long userId, String sessionId, Date sessionValidaty) {
        logger.info("updateSession starts");
//        logger.info("params : [ userId = {} , sessionId = {}, sessionValidaty]", userId, sessionId, sessionValidaty);

        User u = (User) getEntityManager()
                .createQuery("update User e set e.sessionId = ?1, e.sessionValidity = ?2 where e.id = ?3 ")
                .setParameter(1, sessionId)
                .setParameter(2, sessionValidaty)
                .setParameter(3, userId)
                .getSingleResult();

        logger.info("found {} element", u);

        return true;
    }
}