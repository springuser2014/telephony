package telephony.core.dao;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Tax;

/**
 * asd.
 */
public interface TaxDao extends GenericDao<Tax> {

	/**
	 * asd.
	 * @param from a.
	 * @param to ads.
	 * @returna a.
	 */
	Collection<Tax> findInDateRange(Date from, Date to);

}
