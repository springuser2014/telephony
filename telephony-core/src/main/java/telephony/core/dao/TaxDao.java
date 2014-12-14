package telephony.core.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import telephony.core.entity.jpa.Tax;
import telephony.core.query.filter.TaxFilterCriteria;

/**
 * asd.
 */
public interface TaxDao extends GenericDao<Tax> {

	/**
	 * asd.
	 * @param from a.
	 * @param to ads.
	 * @return a.
	 */
	Collection<Tax> findInDateRange(Date from, Date to);

	List<Tax> fetch(TaxFilterCriteria filters);
}
