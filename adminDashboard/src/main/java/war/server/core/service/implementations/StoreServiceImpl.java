package war.server.core.service.implementations;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.StoresDao;
import war.server.core.entity.Store;
import war.server.core.service.interfaces.StoreService;

import javax.persistence.EntityManager;
import java.util.List;


public class StoreServiceImpl implements StoreService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

    @Inject
    private StoresDao storesDao;

    @Override
    public List<Store> fetchAllStores() {
        
        logger.debug("StoreServiceImpl.fetchAllStores starts");
        
        List<Store> stores = storesDao.findUndeleted();
        
        logger.debug("found {} elements " , stores.size());

        return stores;
    }
}
