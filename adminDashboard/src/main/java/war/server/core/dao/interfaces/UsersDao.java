package war.server.core.dao.interfaces;


import war.server.core.entity.User;

import java.util.List;

/**
 * Users management DAO
 */
public interface UsersDao extends GenericDao<User> {

    public List<User> findUndeleted();

}
