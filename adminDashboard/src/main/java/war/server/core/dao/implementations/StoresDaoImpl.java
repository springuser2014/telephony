package war.server.core.dao.implementations;


import war.server.core.dao.interfaces.StoresDao;
import war.server.core.entity.Store;

public class StoresDaoImpl extends GenericDaoImpl<Store> implements StoresDao {

    public StoresDaoImpl() {
        super(Store.class);
    }
}
