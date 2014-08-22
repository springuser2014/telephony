package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Pricing;
import telephony.core.service.bean.Session;

/**
 * ads.
 */
public interface PricingService extends BasicService<Pricing> {

	/**
	 * asd.
	 * @param session TODO
	 * @param from a.
	 * @param to a.
	 * @return a.
	 */
	Collection<Pricing> findByDateRange(Session session, Date from, Date to);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a. 
	 * @return a.
	 */
	Pricing findById(Session session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Pricing> findByIds(Session session, Collection<Long> ids);

	/**
	 * ads.
	 * @param session TODO
	 * @param pricing asd.
	 * @return asd.
	 */
	Pricing update(Session session, Pricing pricing);

	/**
	 * ads.
	 * @param session TODO
	 * @param pricings asd.
	 * @return asd.
	 */
	Collection<Pricing> update(Session session, Collection<Pricing> pricings);

	/**
	 * asd.
	 * @param session TODO
	 * @param pricing as.
	 */
	void remove(Session session, Pricing pricing);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param pricings as.
	 */
	void remove(Session session, Collection<Pricing> pricings);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param id asd.
	 */
	void removeById(Session session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 */
	void removeByIds(Session session, Collection<Long> ids);

}
