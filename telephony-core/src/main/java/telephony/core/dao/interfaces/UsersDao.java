package telephony.core.dao.interfaces;


import telephony.core.entity.User;

import java.util.List;

public interface UsersDao extends GenericDao<User> {

    public List<User> findUndeleted();
}
