package telephony.core.service.impl;

import telephony.core.dao.PricingsDao;
import telephony.core.entity.jpa.Pricing;
import telephony.core.service.PricingService;

import com.google.inject.Inject;

/**
 * ad.
 */
public class PricingServiceImpl
extends AbstractBasicService<Pricing> 
implements PricingService {
	
	@Inject
	private PricingsDao pricingsDao;

	@Override
	public long count() {
		
		// TODO Auto-generated method stub
		return 0;
	}

}
