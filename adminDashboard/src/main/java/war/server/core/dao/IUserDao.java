package war.server.core.dao;


import war.server.core.entity.User;

import java.util.List;

public interface IUserDao {

    /**
     * Wyszukuje wszystkie obiekty użytkoników w systemie
     * @return
     */
    List<User> findAll();
}
