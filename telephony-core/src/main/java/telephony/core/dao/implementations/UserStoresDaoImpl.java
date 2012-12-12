package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.UserStoresDao;
import telephony.core.entity.UserStore;

public class UserStoresDaoImpl extends GenericDaoImpl<UserStore> implements  UserStoresDao {
    public UserStoresDaoImpl() {
        super(UserStore.class);
    }
}
