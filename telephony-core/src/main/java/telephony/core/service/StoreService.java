package telephony.core.service;

import telephony.core.entity.jpa.Store;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreDeleteResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.StoreServiceException;

public interface StoreService extends BasicService<Store> {

	StoreFetchResponse fetch(StoreFetchRequest request)
		throws SessionServiceException, StoreServiceException;

	StoreAddResponse add(StoreAddRequest request)
		throws SessionServiceException, StoreServiceException;

	StoreDeleteResponse delete(StoreDeleteRequest request)
		throws SessionServiceException, StoreServiceException;

	StoreEditResponse edit(StoreEditRequest request)
		throws SessionServiceException, StoreServiceException;
}
