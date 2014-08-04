package telephony.core.dao;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Pricing;

/**
 * asd.
 */
public interface PricingsDao extends GenericDao<Pricing>{
	
	/**
	 * asd.
	 * @param from a.
	 * @param to a.
	 * @return a.
	 */
	Collection<Pricing> findByDateRange(Date from, Date to);

}
