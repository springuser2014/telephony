package telephony.core.dao.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;

/**
 * Stores management DAO.
 */
public class StoresDaoImpl 
extends GenericDaoImpl<Store> 
implements StoresDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public StoresDaoImpl() {
        super(Store.class);
    }


	@Override
	public Store findByLabel(String storelabel) {
		
		logger.info("findByLabel starts");
		logger.info("param : [ storelabel : {} ]", storelabel);
		
		Store s = (Store) getEntityManager()
				.createQuery("select e from Store e where e.label = ?1")
				.setParameter(1, storelabel).getSingleResult();
		
		return s;
	}


	@Override
	public List<Store> find(StoreFilterCriteria filters) {

		return new ArrayList<Store>();
	}
}