package telephony.gwt.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.gwt.client.service.StoreRPCService;
import telephony.core.entity.Store;
import telephony.core.service.interfaces.StoreService;
import telephony.gwt.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;


/**
 * @todo Remove after migration to REST services
 */
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
