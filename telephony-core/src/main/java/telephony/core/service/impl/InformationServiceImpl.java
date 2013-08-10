package telephony.core.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.SalesDao;
import telephony.core.entity.jpa.Store;
import telephony.core.service.InformationService;

import com.google.inject.Inject;


/**
 * Information management service.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class InformationServiceImpl implements InformationService {


    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private SalesDao salesDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    @Override
    public final long getNumberOfDeliveries(final Store store) {
        logger.debug("InformationServiceImpl.getNumberOfDeliveries starts");

        return deliveriesDao.getNumberOfDeliveries(store);
    }

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    @Override
    public final long getNumberOfSales(final Store store) {

        logger.debug("InformationServiceImpl.getNumberOfSales starts");

        return salesDao.getNumberOfSales(store);
    }
}
