package telephony.core.service;

import java.util.Collection;
import java.util.Date;

import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.request.TaxesFetchRequest;
import telephony.core.service.dto.response.TaxAddResponse;
import telephony.core.service.dto.response.TaxDeleteResponse;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.dto.response.TaxFetchResponse;

/**
 * asd.
 */
public interface TaxService extends BasicService<Tax> {

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 */
	TaxFetchResponse fetch(TaxesFetchRequest request);

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	TaxAddResponse add(TaxAddRequest request);


	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 */
	TaxEditResponse edit(TaxEditRequest request);

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	TaxDeleteResponse delete(TaxDeleteRequest request);

	// TODO delete the stuff below

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
