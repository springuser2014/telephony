package telephony.server.core.dao.implementations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.core.dao.interfaces.StoresDao;
import telephony.server.core.entity.Store;

import java.util.List;

public class StoresDaoImpl extends GenericDaoImpl<Store> implements StoresDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public StoresDaoImpl() {
        super(Store.class);
    }
    
    public List<Store> findUndeleted() {

        logger.debug("findUndeleted starts ");

        List<Store> lst = em.createQuery(" select e from Store e " +
                                         " where e.deleter is null")
                            .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

}