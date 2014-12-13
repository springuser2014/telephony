package telephony.core.service;

import java.util.Collection;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ProducerDeleteRequest;
import telephony.core.service.dto.request.ProducerEditRequest;
import telephony.core.service.dto.request.ProducersFetchRequest;
import telephony.core.service.dto.response.ProducerDeleteResponse;
import telephony.core.service.dto.response.ProducerEditResponse;
import telephony.core.service.dto.response.ProducersFetchResponse;
import telephony.core.service.exception.ProducerServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface ProducerService extends BasicService<Producer> {

	/**
	 * sd.
	 * @param request d.
	 * @return d.
	 * @throws SessionServiceException d.
	 * @throws ProducerServiceException d.
	 */
	ProducersFetchResponse fetch(ProducersFetchRequest request)
		throws SessionServiceException, ProducerServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return d.
	 * @throws SessionServiceException d.
	 * @throws ProducerServiceException d.
	 */
	ProducerEditResponse edit(ProducerEditRequest request)
		throws SessionServiceException, ProducerServiceException;

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 * @throws SessionServiceException a.
	 * @throws ProducerServiceException a.
	 */
	ProducerDeleteResponse delete(ProducerDeleteRequest request)
		throws SessionServiceException, ProducerServiceException;
}
