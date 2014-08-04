package telephony.core.service.impl;

import java.util.Collection;
import java.util.Date;

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
		
		logger.debug("PricingServiceImpl.count starts");

		return pricingsDao.count();
	}

	@Override
	public Collection<Pricing> findByDateRange(Date from, Date to) {

		logger.debug("PricingServiceImpl.findByDateRange starts");        
		logger.debug("params : [ from: {}, to : {} ]", from, to);

		return pricingsDao.findByDateRange(from, to);
	}

}
