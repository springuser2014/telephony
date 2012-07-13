package telephony.server.core.service.interfaces;

import telephony.server.core.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();
}
