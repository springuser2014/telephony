package telephony.core.dao;

import java.util.Date;

import telephony.core.entity.jpa.Pricing;

/**
 * asd.
 */
public interface PricingsDao extends GenericDao<Pricing>{
	
	/**
	 * asd. 
	 * @param from a.
	 * @param to .da
	 */
	void findInDateRange(Date from, Date to);

}
