package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface ProducerService extends BasicService<Producer> {

	/**
	 * ads.
	 * @param session TODO
	 * @param label a.
	 * @return a.
	 */
	Producer findByLabel(SessionDto session, String label);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return a.
	 */
	Producer findById(SessionDto session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return d.
	 */
	Collection<Producer> findById(SessionDto session, Collection<Long> ids);

	/**
	 * ads.
	 * @param session TODO
	 * @param producer a.
	 * @return asd.
	 */
	Producer update(SessionDto session, Producer producer);

	/**
	 * asd.
	 * @param session TODO
	 * @param producers a.
	 * @return a.
	 */
	Collection<Producer> update(SessionDto session, Collection<Producer> producers);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 */
	void removeById(SessionDto session, Long id);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids asd.
	 */
	void removeById(SessionDto session, Collection<Long> ids);
}
