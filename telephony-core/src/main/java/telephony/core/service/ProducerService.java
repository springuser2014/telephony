package telephony.core.service;

import telephony.core.entity.jpa.BaseEntity;
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
}
