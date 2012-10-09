package telephony.server.core.dao.implementations;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.core.dao.interfaces.InformationDao;

import javax.persistence.EntityManager;


public class InformationDaoImpl implements InformationDao {

    private Logger logger =  LoggerFactory.getLogger(getClass());

    @Inject
    protected EntityManager em;

    public Long getNumberOfDeliveries() {
        logger.debug("InformationDaoImpl.getNumberOfDeliveries starts");

        return em.createQuery("select count(d) from Delivery d", Long.class).getSingleResult();
    }

    public Long getNumberOfSales() {
        logger.debug("InformationDaoImpl.getNumberOfSales starts");

        return em.createQuery("select count(d) from Sale d", Long.class).getSingleResult();
    }
}
