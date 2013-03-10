package telephony.core.dao.impl;

import telephony.core.dao.UserStoresDao;
import telephony.core.entity.jpa.UserStore;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserStoresDaoImpl extends GenericDaoImpl<UserStore> implements  UserStoresDao {

    /**
     * asd.
     */
    public UserStoresDaoImpl() {
        super(UserStore.class);
    }
}
