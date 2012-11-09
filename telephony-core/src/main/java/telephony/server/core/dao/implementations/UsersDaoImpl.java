package telephony.server.core.dao.implementations;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.core.dao.interfaces.UsersDao;
import telephony.server.core.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UsersDaoImpl extends GenericDaoImpl<User> implements UsersDao {

    public UsersDaoImpl() {
        super(User.class);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;


    public List<User> findUndeleted() {

        logger.debug("findUndeleted starts ");
        logger.debug("entity type : {} ", entityClass.getName());

        List<User> lst = em.createQuery("select e from User e where e.deleter is null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }
}