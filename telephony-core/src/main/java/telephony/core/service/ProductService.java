package telephony.core.service;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.ModelDto;
import telephony.core.service.dto.ProducerDto;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.ProductFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface ProductService extends BasicService<Product> {

	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 * @throws SessionServiceException a.
	 */
	ProductFetchResponse fetch(ProductFetchRequest req)
			throws SessionServiceException;

    /**
     * asd.
     * @param session d.
     * @return asd.
     */
	List<String> fetchAllColors(SessionDto session) throws SessionServiceException;

	/**
	 * asd.
	 * @param session a.
	 * @return a.
	 */
	List<ProducerDto> fetchAllProducersInUse(SessionDto session) throws SessionServiceException;

	/**
	 * asd.
	 * @param session a.
	 * @return a.
	 */
	List<String> fetchAllImeiInUse(SessionDto session) throws SessionServiceException;

	/**
	 * asd.
	 * @param session a.
	 * @return a.
	 */
	List<ModelDto> fetchAllModelsInUse(SessionDto session) throws SessionServiceException;
}
