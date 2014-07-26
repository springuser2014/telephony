package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Tax;

/**
 * asd.
 */
public class TaxDaoImpl extends GenericDaoImpl<Tax> implements TaxDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public TaxDaoImpl() {
		super(Tax.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tax> findInDateRange(Date from, Date to) {
        logger.debug("findById starts ");
        logger.debug("params: [ from : {}, to : {} ]  ", from, to);
        
        
        if (from != null && to != null && from.getTime() >= to.getTime()) {
        	return new ArrayList<Tax>();
        }
        
        if (from != null && to != null)  {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
	            .createQuery("select t from Tax t where (t.from >= :from and t.to < :to) " +
	            		"or (to.from IS NULL and t.to < :to) " +
	            		"or (to.from >= :from and t.to IS NULL) ")
	            .setParameter("from", from)
	            .setParameter("to", to)
	            .getResultList();
        	
        	return col;

        } else if (from != null && to == null) {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t where t.from < :from")    	            		
            .setParameter("from", from)
            .getResultList();
        	
        	return col;
        	
        } else if (from == null &&  to != null) {
        	
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t where t.to < :to")
            .setParameter("to", to)
            .getResultList();
        	
        	return col;
            	
        } else { 
        // from == null && to == null
        	List<Tax> col = (List<Tax>) getEntityManager()
            .createQuery("select t from Tax t")
            .getResultList();
        	
        	return col;
        }        
	}
}
