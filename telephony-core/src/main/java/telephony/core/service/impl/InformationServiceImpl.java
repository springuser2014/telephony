package telephony.core.service.impl;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.DeliveriesDao;
import telephony.core.dao.interfaces.SalesDao;
import telephony.core.entity.Store;
import telephony.core.service.interfaces.InformationService;

/**
 * @todo complete later
 */
public class InformationServiceImpl implements InformationService {

    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private SalesDao salesDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public long getNumberOfDeliveries(Store store) {
        logger.debug("InformationServiceImpl.getNumberOfDeliveries starts");

        return deliveriesDao.getNumberOfDeliveries(store);
    }

    public long getNumberOfSales(Store store) {

        logger.debug("InformationServiceImpl.getNumberOfSales starts");

        return salesDao.getNumberOfSales(store);
    }
}
