package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.UserStoresDao;
import telephony.core.entity.UserStore;

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
