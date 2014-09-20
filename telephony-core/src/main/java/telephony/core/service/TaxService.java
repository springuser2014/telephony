package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface TaxService extends BasicService<Tax> {

	/**
	 * asd.
	 * @param session TODO
	 * @param tax a.
	 */
	void add(SessionDto session, Tax tax);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return a.
	 */
	Tax findById(SessionDto session, Long id);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Tax> findByIds(SessionDto session, Collection<Long> ids);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param from a.
	 * @param to a. 
	 * @return asd.
	 */
	Collection<Tax> findInDateRange(SessionDto session, Date from, Date to);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param taxesToUpdate a.
	 * @return a.
	 */
	Collection<Tax> update(SessionDto session, Collection<Tax> taxesToUpdate);

	/**
	 * asd.
	 * @param session TODO
	 * @param taxesToDelete sad.
	 */
	void remove(SessionDto session, Collection<Tax> taxesToDelete);

	/**
	 * asd.
	 * @param session TODO
	 * @param tax a.
	 * @return asd.
	 */
	Tax update(SessionDto session, Tax tax);

	/**
	 * asd.
	 * @param session TODO
	 * @param taxToDelete a.
	 */
	void remove(SessionDto session, Tax taxToDelete);
	
}
