package telephony.core.service.impl;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.StoresDao;
import telephony.core.entity.Store;
import telephony.core.service.interfaces.StoreService;

import java.util.List;


public class StoreServiceImpl implements StoreService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private StoresDao storesDao;

    public List<Store> fetchAllStores() {

        logger.debug("StoreServiceImpl.fetchAllStores starts");

        List<Store> stores = storesDao.findUndeleted();

        logger.debug("found {} elements ", stores.size());

        return stores;
    }
}
