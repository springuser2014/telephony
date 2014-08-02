package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Producer;

/**
 * asd.
 */
public interface ProducerService extends BasicService<Producer> {

	/**
	 * ads.
	 * @param label a.
	 * @return a.
	 */
	Producer findByLabel(String label);

	/**
	 * asd.
	 * @param id
	 * @return a.
	 */
	Producer findById(long id);

	/**
	 * asd.
	 * @param ids a.
	 * @return d.
	 */
	Collection<Producer> findById(Collection<Long> ids);

	/**
	 * ads.
	 * @param p a.
	 * @return asd.
	 */
	Producer update(Producer p);

	/**
	 * asd.
	 * @param coll a.
	 * @return a.
	 */
	Collection<Producer> update(Collection<Producer> coll);

	/**
	 * asd.
	 * @param id a.
	 */
	void removeById(Long id);
	
	/**
	 * asd.
	 * @param ids asd.
	 */
	void removeById(Collection<Long> ids);
}
