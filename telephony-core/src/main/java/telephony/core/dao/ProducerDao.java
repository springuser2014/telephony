package telephony.core.dao;

import telephony.core.entity.jpa.Producer;
import telephony.core.query.filter.ProducerFilterCriteria;

import java.util.List;

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

	/**
	 * asd.
	 * @param filters asd.
	 * @return sd.
	 */
	List<Producer> fetch(ProducerFilterCriteria filters);
}
