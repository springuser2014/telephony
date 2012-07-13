package telephony.server.core.dao.interfaces;


import telephony.server.core.entity.User;

import java.util.List;

public interface UsersDao extends GenericDao<User> {

    public List<User> findUndeleted();
}
