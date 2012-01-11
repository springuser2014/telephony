package war.server.core.dao;


import war.server.core.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    public UserDao() {
    }

    public List<User> findAll() {
        return new ArrayList<User>();
    }
}

