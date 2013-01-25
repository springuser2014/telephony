package telephony.core.dao.interfaces;


import telephony.core.entity.User;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface UsersDao extends GenericDao<User> {

    public List<User> findUndeleted();

    User findByName(String name);

    User findByNameAndPassword(String name, String password) throws NoSuchElementException;

    boolean updateSession(Long userId, String sessionId, Date sessionValidaty);
}
