package telephony.core.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import telephony.core.entity.jpa.Tax;
import telephony.core.query.filter.TaxFilterCriteria;

public interface TaxDao extends GenericDao<Tax> {

	Collection<Tax> findInDateRange(Date from, Date to);

	List<Tax> fetchByCriteria(TaxFilterCriteria filters);

	Long countByCriteria(TaxFilterCriteria filters);
}
