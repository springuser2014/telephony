package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Pricing;
import telephony.core.service.dto.SessionDto;

/**
 * ads.
 */
// TODO : think if this service is necessary, maybe should be merged with ProductService
@Deprecated
public interface PricingService extends BasicService<Pricing> {
	
	/**
	 * asd.
	 * @param session TODO
	 * @param from a.
	 * @param to a.
	 * @return a.
	 */
	Collection<Pricing> findByDateRange(SessionDto session, Date from, Date to);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a. 
	 * @return a.
	 */
	Pricing findById(SessionDto session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Pricing> findByIds(SessionDto session, Collection<Long> ids);

	/**
	 * ads.
	 * @param session TODO
	 * @param pricing asd.
	 * @return asd.
	 */
	Pricing update(SessionDto session, Pricing pricing);

	/**
	 * ads.
	 * @param session TODO
	 * @param pricings asd.
	 * @return asd.
	 */
	Collection<Pricing> update(SessionDto session, Collection<Pricing> pricings);

	/**
	 * asd.
	 * @param session TODO
	 * @param pricing as.
	 */
	void remove(SessionDto session, Pricing pricing);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param pricings as.
	 */
	void remove(SessionDto session, Collection<Pricing> pricings);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param id asd.
	 */
	void removeById(SessionDto session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 */
	void removeByIds(SessionDto session, Collection<Long> ids);

}
