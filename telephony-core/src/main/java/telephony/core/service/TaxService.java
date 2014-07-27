package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Tax;

/**
 * asd.
 */
public interface TaxService extends BasicService<Tax> {

	/**
	 * asd.
	 * @param tax a.
	 */
	void addTax(Tax tax);
	
	/**
	 * asd.
	 * @param id a.
	 * @return a.
	 */
	Tax findById(Long id);
	
	/**
	 * asd.
	 * @param ids a.
	 * @return a.
	 */
	Collection<Tax> findByIds(Collection<Long> ids);
	
	/**
	 * asd.
	 * @param from a.
	 * @param to a. 
	 * @return asd.
	 */
	Collection<Tax> findInDateRange(Date from, Date to);
	
	/**
	 * asd.
	 * @param taxesToUpdate a.
	 * @return a.
	 */
	Collection<Tax> update(Collection<Tax> taxesToUpdate);

	/**
	 * asd.
	 * @param taxesToDelete sad.
	 */
	void delete(Collection<Tax> taxesToDelete);

	/**
	 * asd.
	 * @param tax a.
	 * @return asd.
	 */
	Tax update(Tax tax);

	/**
	 * asd.
	 * @param taxToDelete a.
	 */
	void delete(Tax taxToDelete);
	
	
}
