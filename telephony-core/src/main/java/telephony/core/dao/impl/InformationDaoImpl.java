package telephony.core.dao.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.InformationDao;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class InformationDaoImpl implements InformationDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    /**
     * asd.
     * @return asd.
     */
    protected EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public Long getNumberOfDeliveries() {
        logger.debug("InformationDaoImpl.getNumberOfDeliveries starts");

        return getEntityManager().createQuery("select count(d) from Delivery d", Long.class)
                        .getSingleResult();
    }

    @Override
    public Long getNumberOfSales() {
        logger.debug("InformationDaoImpl.getNumberOfSales starts");

        return getEntityManager().createQuery("select count(d) from Sale d", Long.class)
                        .getSingleResult();
    }
}
