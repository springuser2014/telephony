package telephony.core.service.interfaces;

import telephony.core.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();
}
