package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import telephony.core.dao.PricingsDao;
import telephony.core.entity.jpa.Pricing;
import telephony.core.entity.jpa.Tax;

/**
 * asd.
 */
public class PricingsDaoImpl 
extends GenericDaoImpl<Pricing> 
implements PricingsDao {
	
	/**
	 * 
	 */
	public PricingsDaoImpl() {
		super(Pricing.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Pricing> findByDateRange(Date from, Date to) {
	
		logger.debug("PricingsDaoImpl.findInDateRange starts");
		logger.debug("params : [ from : {}, to : {} ]");
		
	   if (from != null && to != null && from.getTime() >= to.getTime()) {
        	return new ArrayList<Pricing>();
       }
	   
	   Query q = null; 
	     
		if (from != null && to != null) {
				
			String qStr = 
			 "select p from Pricing p where (p.from >= :from and p.to <= :to) "
			+ "or (p.from IS NULL and p.to >= :from and p.to <= :to ) " 
    		+ "or (p.from <= :to and p.from <= :from and p.to IS NULL) ";
            			
			q = getEntityManager().createQuery(qStr);
			
			q.setParameter("from", from);
			q.setParameter("to", to);
				
		} else if (from != null && to == null) {

			String qStr = "select p from Pricing p where p.from >= :from and p.to IS NULL";
			q = getEntityManager().createQuery(qStr);
			q.setParameter("from", from);
			
		} else if (from == null && to != null) {

			String qStr = "select p from Pricing p where p.from IS NULL and p.to < :to";
			q = getEntityManager().createQuery(qStr);
			q.setParameter("to", to);

		} else {
			
			String qStr = "select p from Pricing p where p.from IS NULL and p.to IS NULL";
			q = getEntityManager().createQuery(qStr);
		}
		
		List<Pricing> res = (List<Pricing>) q.getResultList();
		
		logger.info(" found {} elements", res.size());
		
		return res;		
	}

}
