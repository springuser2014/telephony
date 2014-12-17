package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreDeleteResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.StoreServiceException;

/**
 * asd.
 */
public interface StoreService extends BasicService<Store> {

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException d.
	 * @throws StoreServiceException d.
	 */
	StoreFetchResponse fetch(StoreFetchRequest request)
		throws SessionServiceException, StoreServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException a.
	 * @throws StoreServiceException d.
	 */
	StoreAddResponse add(StoreAddRequest request)
		throws SessionServiceException, StoreServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException a.
	 * @throws StoreServiceException d.
	 */
	StoreDeleteResponse delete(StoreDeleteRequest request)
		throws SessionServiceException, StoreServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException a.
	 * @throws StoreServiceException a.
	 */
	StoreEditResponse edit(StoreEditRequest request)
		throws SessionServiceException, StoreServiceException;

}
