package telephony.core.dao.implementations;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.InformationDao;

import javax.persistence.EntityManager;


public class InformationDaoImpl implements InformationDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    protected Provider<EntityManager> entityManagerProvider;

    protected EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    public Long getNumberOfDeliveries() {
        logger.debug("InformationDaoImpl.getNumberOfDeliveries starts");

        return getEntityManager().createQuery("select count(d) from Delivery d", Long.class).getSingleResult();
    }

    public Long getNumberOfSales() {
        logger.debug("InformationDaoImpl.getNumberOfSales starts");

        return getEntityManager().createQuery("select count(d) from Sale d", Long.class).getSingleResult();
    }
}
