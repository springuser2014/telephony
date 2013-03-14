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
}