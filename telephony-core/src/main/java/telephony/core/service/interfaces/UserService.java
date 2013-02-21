package telephony.core.service.interfaces;

import java.util.List;

import telephony.core.entity.User;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UserService {

    /**
     * asd.
     * @return asd.
     */
    List<User> findAllUsers();

    /**
     * asd.
     * @param name asd.
     * @return asd.
     */
    User findUserByName(String name);


}
