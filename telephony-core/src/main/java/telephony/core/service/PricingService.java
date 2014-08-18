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

	/**
	 * asd.
	 * @param id a. 
	 * @return a.
	 */
	Pricing findById(long id);

	/**
	 * asd.
	 * @param ids a.
	 * @return a.
	 */
	Collection<Pricing> findByIds(Collection<Long> ids);

	/**
	 * ads.
	 * @param pricing asd.
	 * @return asd.
	 */
	Pricing update(Pricing pricing);

	/**
	 * ads.
	 * @param pricings asd.
	 * @return asd.
	 */
	Collection<Pricing> update(Collection<Pricing> pricings);

	/**
	 * asd.
	 * @param p as.
	 */
	void remove(Pricing p);
	
	/**
	 * asd.
	 * @param pricings as.
	 */
	void remove(Collection<Pricing> pricings);
	
	/**
	 * asd.
	 * @param id asd.
	 */
	void removeById(long id);

	/**
	 * asd.
	 * @param ids a.
	 */
	void removeByIds(Collection<Long> ids);

}
