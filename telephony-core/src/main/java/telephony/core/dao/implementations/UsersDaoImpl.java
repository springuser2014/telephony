package telephony.core.dao.implementations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;

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
}