package telephony.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Store;
import telephony.core.service.StoreService;

import com.google.inject.Inject;


/**
 * Stores management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoreServiceImpl extends AbstractBasicService<Store> implements StoreService {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private StoresDao storesDao;

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final List<Store> fetchAllStores() {

        logger.debug("StoreServiceImpl.fetchAllStores starts");

        List<Store> stores = storesDao.find();

        logger.debug("found {} elements ", stores.size());

        return stores;
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public long count() {
		
		return storesDao.count();
	}
}
