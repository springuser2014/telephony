package war.server.core.dao.implementations;


import war.server.core.dao.interfaces.UsersDao;
import war.server.core.entity.User;

public class UsersDaoImpl extends GenericDaoImpl<User> implements UsersDao {

    public UsersDaoImpl() {
        super(User.class);
    }

//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Inject
//    private EntityManager em;
//
//    public UserDaoImpl() {
//    }
//
//    public List<User> findAll() {
//
//        logger.debug("findAll stars ");
//
//        List<User> res = em.createQuery("select u from User u").getResultList();
//
//        logger.debug("findAll found {} elements", res.size());
//
//        return res;
//    }
//
//    @Override
//    public List<User> findUndeleted() {
//
//        logger.debug("findUndeleted stars ");
//
//        List<User> res = em.createQuery("select u from User u where deletedAt is null ").getResultList();
//
//        logger.debug("findUndeleted found {} elements", res.size());
//
//        return res;
//    }
//
//    @Override
//    public List<User> findDeleted() {
//
//        logger.debug("findDeleted stars ");
//
//        List<User> res = em.createQuery("select u from User u where deletedAt is not null ").getResultList();
//
//        logger.debug("findDeleted found {} elements", res.size());
//
//        return res;
//    }
//
//    @Override
//    public User findById(Long id) {
//
//        logger.debug("findById stars ");
//        logger.debug("params : [ id : {} ]", id);
//
//        User res = (User)em.createQuery("select u from User u where id = ?1 ")
//                           .setParameter(1, id)
//                           .getSingleResult();
//
//        logger.debug("findById found element with id {} ", res);
//
//        return res;
//    }
//
//    @Override
//    public List<User> findByIds(List<Long> ids) {
//
//        logger.debug("findByIds stars ");
//        logger.debug("number of params : {} ", ids.size());
//
//        String ids_string = "";
//
//        for(int c = 0; c < ids.size(); c++) {
//
//            if (c > 0)
//                ids_string += " , ";
//
//            ids_string += ids.get(c);
//        }
//
//        List<User> res = em.createQuery("select u from User u where id in (?1)")
//                           .setParameter(1, ids_string)
//                           .getResultList();
//
//        logger.debug("findByIds found number of elements : {} ", res.size());
//
//        return res;
//    }
//
//    @Override
//    public List<User> findUndeletedByIds(List<Long> ids) {
//
//        logger.debug("findUndeletedByIds stars ");
//        logger.debug("number of params : {} ", ids.size());
//
//        String ids_string = "";
//
//        for(int c = 0; c < ids.size(); c++) {
//
//            if (c > 0)
//                ids_string += " , ";
//
//            ids_string += ids.get(c);
//        }
//
//        List<User> res = em.createQuery("select u from User u where id in (?1) and deletedBy is null")
//                           .setParameter(1, ids_string)
//                           .getResultList();
//
//        logger.debug("findUndeletedByIds found number of elements : {} ", res.size());
//
//        return res;
//    }
//
//    @Override
//    public List<User> findDeletedByIds(List<Long> ids) {
//
//        logger.debug("findDeletedByIds stars ");
//        logger.debug("number of params : {} ", ids.size());
//
//        String ids_string = "";
//
//        for(int c = 0; c < ids.size(); c++) {
//
//            if (c > 0)
//                ids_string += " , ";
//
//            ids_string += ids.get(c);
//        }
//
//        List<User> res =   em.createQuery("select u from User u where id in (?1) and deletedBy is not null")
//                           .setParameter(1, ids_string)
//                           .getResultList();
//
//        logger.debug("findDeletedByIds found number of elements : {} ", res.size());
//
//        return res;
//    }
//
//    public User find(Long id) {
//
//        logger.debug("find stars ");
//        logger.debug("params : [ id : {} ]", id);
//
//        List<User> res = em.createQuery("select u from User u where u.id = ?1")
//                           .setParameter(1, id)
//                           .getResultList();
//
//        logger.debug("found : {} elements", res.size());
//
//        if (res.size() == 1) {
//            return res.get(0);
//        }
//        else {
//            return null;
//        }
//    }
//
//    public void delete(Long id) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void save(User user) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void save(List<User> users) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void saveOrUpdate(User user) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    public void saveOrUpdate(List<User> users) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void permanentDeleteById(Long id) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void permanentDeleteByIds(List<Long> ids) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void delete(User entity) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }
//
//    @Override
//    public void delete(List<User> entities) {
//        //To change body of implemented methods use File | Settings | File Templates.
//    }

}

