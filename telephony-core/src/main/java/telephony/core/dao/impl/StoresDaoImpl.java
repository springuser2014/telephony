package telephony.core.dao.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Store;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoresDaoImpl extends GenericDaoImpl<Store> implements StoresDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public StoresDaoImpl() {
        super(Store.class);
    }


	@SuppressWarnings("unchecked")
    @Override
    public List<Store> findNotRemoved() {

        logger.debug("findUndeleted starts ");

        List<Store> lst = getEntityManager().createQuery(" select e from Store e "
                + " where e.deleter is null")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

}