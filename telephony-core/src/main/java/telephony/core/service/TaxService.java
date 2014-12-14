package telephony.core.service;

import telephony.core.entity.jpa.Tax;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxAddResponse;
import telephony.core.service.dto.response.TaxDeleteResponse;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.dto.response.TaxFetchResponse;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface TaxService extends BasicService<Tax> {

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 */
	TaxFetchResponse fetch(TaxFetchRequest request) throws SessionServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	TaxAddResponse add(TaxAddRequest request) throws SessionServiceException;


	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 */
	TaxEditResponse edit(TaxEditRequest request) throws SessionServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	TaxDeleteResponse delete(TaxDeleteRequest request) throws SessionServiceException;
}
