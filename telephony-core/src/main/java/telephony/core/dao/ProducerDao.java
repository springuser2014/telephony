package telephony.core.dao;

import telephony.core.entity.jpa.Producer;

/**
 * asd.
 */
public interface ProducerDao extends GenericDao<Producer> {

	/**
	 * asd.
	 * @param label a.
	 * @return d.
	 */
	Producer findByLabel(String label);

}
