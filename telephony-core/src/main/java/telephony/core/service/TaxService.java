package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.Session;

/**
 * asd.
 */
public interface TaxService extends BasicService<Tax> {

	/**
	 * asd.
	 * @param session TODO
	 * @param tax a.
	 */
	void add(Session session, Tax tax);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return a.
	 */
	Tax findById(Session session, Long id);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Tax> findByIds(Session session, Collection<Long> ids);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param from a.
	 * @param to a. 
	 * @return asd.
	 */
	Collection<Tax> findInDateRange(Session session, Date from, Date to);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param taxesToUpdate a.
	 * @return a.
	 */
	Collection<Tax> update(Session session, Collection<Tax> taxesToUpdate);

	/**
	 * asd.
	 * @param session TODO
	 * @param taxesToDelete sad.
	 */
	void remove(Session session, Collection<Tax> taxesToDelete);

	/**
	 * asd.
	 * @param session TODO
	 * @param tax a.
	 * @return asd.
	 */
	Tax update(Session session, Tax tax);

	/**
	 * asd.
	 * @param session TODO
	 * @param taxToDelete a.
	 */
	void remove(Session session, Tax taxToDelete);
	
}
