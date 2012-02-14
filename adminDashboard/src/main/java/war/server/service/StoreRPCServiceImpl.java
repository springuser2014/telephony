package war.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.StoreRPCService;
import war.server.core.entity.Store;
import war.server.core.service.interfaces.StoreService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;


@SuppressWarnings("serial")
@Singleton
public class StoreRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements StoreRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    private StoreService storeService;

    @Inject
    public StoreRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    @Override
    public List<Store> fetchAllStores() {
        logger.debug("StoreRPCServiceImpl.fetchAllStores starts");
        
        List<Store> stores = storeService.fetchAllStores();

        logger.debug("found {} elements", stores.size());

        return stores;
    }
}
