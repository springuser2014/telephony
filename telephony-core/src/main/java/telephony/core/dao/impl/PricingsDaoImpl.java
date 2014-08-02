package telephony.core.dao.impl;

import java.util.Date;

import telephony.core.dao.PricingsDao;
import telephony.core.entity.jpa.Pricing;

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

	@Override
	public void findInDateRange(Date from, Date to) {
		// TODO Auto-generated method stub
		
	}

}
