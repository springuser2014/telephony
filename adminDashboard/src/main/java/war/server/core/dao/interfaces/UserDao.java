package war.server.core.dao.interfaces;


import war.server.core.entity.User;

import java.util.List;

/**
 * Users management DAO
 */
public interface UserDao {

    /**
     * Finds all users in db
     * @return
     */
    List<User> findAll();

    /**
     * Finds user with specified by id
     *
     * @param id
     * @return
     */
    User find(Long id);


    /**
     * Finds users specified by ids group
     *
     * @param ids
     * @return
     */
    List<User> find(List<Long> ids);

    /**
     * Deletes specified user from db
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Deletes specified users from db
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * Saves given user entity
     * @param user
     */
    void save(User user);

    /**
     * Saves all given user entity
     * @param user
     */
    void save(List<User> users);

    /**
     * Saves user (if entity doesn't exist) or update (if exists) in db
     * @param user
     */
    void saveOrUpdate(User user);

    /**
     * Saves group of users (if entity doesn't exist) or update (if exists) in db
     * @param user
     */
    void saveOrUpdate(List<User> users);


}
