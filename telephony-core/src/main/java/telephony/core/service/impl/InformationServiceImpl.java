package telephony.core.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.interfaces.DeliveriesDao;
import telephony.core.dao.interfaces.SalesDao;
import telephony.core.entity.Store;
import telephony.core.service.interfaces.InformationService;

import com.google.inject.Inject;


// TODO : complete later
/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class InformationServiceImpl implements InformationService {

    /**
     * asd.
     */
    @Inject
    private DeliveriesDao deliveriesDao;

    /**
     * asd.
     */
    @Inject
    private SalesDao salesDao;

    /**
     * asd.
     */
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
