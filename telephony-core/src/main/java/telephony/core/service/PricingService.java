package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Pricing;

/**
 * ads.
 */
public interface PricingService extends BasicService<Pricing> {

	/**
	 * asd.
	 * @param from a.
	 * @param to a.
	 * @return a.
	 */
	Collection<Pricing> findByDateRange(Date from, Date to);

}
