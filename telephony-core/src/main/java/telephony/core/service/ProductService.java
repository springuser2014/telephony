package telephony.core.service;

import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ProductCheckImeiRequest;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.request.ProductFetchDataRequest;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.ProductCheckImeiResponse;
import telephony.core.service.dto.response.ProductDetailsResponse;
import telephony.core.service.dto.response.ProductFetchDataResponse;
import telephony.core.service.dto.response.ProductFetchResponse;
import telephony.core.service.exception.SessionServiceException;

import java.util.List;

public interface ProductService extends BasicService<Product> {

	ProductFetchResponse fetch(ProductFetchRequest req)
			throws SessionServiceException;

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
