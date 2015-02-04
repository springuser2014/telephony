package telephony.core.service;

import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.ProductServiceException;
import telephony.core.service.exception.SessionServiceException;

import java.util.List;

public interface ProductService extends BasicService<Product> {

	ProductFetchResponse fetch(ProductFetchRequest req)
			throws SessionServiceException;

	ProductEditResponse edit(ProductEditRequest req)
			throws SessionServiceException, ProductServiceException;

	List<String> fetchAllColors(SessionDto session)
			throws SessionServiceException;

	List<ProducerDto> fetchAllProducersInUse(SessionDto session)
			throws SessionServiceException;

	List<String> fetchAllImeiInUse(SessionDto session)
			throws SessionServiceException;

	List<ModelDto> fetchAllModelsInUse(SessionDto session)
			throws SessionServiceException;

	ProductDetailsResponse fetchDetails(ProductDetailsRequest request)
			throws SessionServiceException;

	ProductFetchDataResponse fetchData(ProductFetchDataRequest request)
			throws SessionServiceException;

	ProductCheckImeiResponse checkImei(ProductCheckImeiRequest request)
			throws SessionServiceException;
}
