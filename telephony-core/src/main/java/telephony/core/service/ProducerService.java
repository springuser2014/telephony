package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.bean.Session;

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
	Producer findByLabel(Session session, String label);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return a.
	 */
	Producer findById(Session session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return d.
	 */
	Collection<Producer> findById(Session session, Collection<Long> ids);

	/**
	 * ads.
	 * @param session TODO
	 * @param producer a.
	 * @return asd.
	 */
	Producer update(Session session, Producer producer);

	/**
	 * asd.
	 * @param session TODO
	 * @param producers a.
	 * @return a.
	 */
	Collection<Producer> update(Session session, Collection<Producer> producers);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 */
	void removeById(Session session, Long id);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids asd.
	 */
	void removeById(Session session, Collection<Long> ids);
}
