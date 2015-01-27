package telephony.core.service;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.dto.request.ProducerDeleteRequest;
import telephony.core.service.dto.request.ProducerEditRequest;
import telephony.core.service.dto.request.ProducersFetchRequest;
import telephony.core.service.dto.response.ProducerDeleteResponse;
import telephony.core.service.dto.response.ProducerEditResponse;
import telephony.core.service.dto.response.ProducersFetchResponse;
import telephony.core.service.exception.ProducerServiceException;
import telephony.core.service.exception.SessionServiceException;

public interface ProducerService extends BasicService<Producer> {

	ProducersFetchResponse fetch(ProducersFetchRequest request)
		throws SessionServiceException, ProducerServiceException;

	ProducerEditResponse edit(ProducerEditRequest request)
		throws SessionServiceException, ProducerServiceException;

	ProducerDeleteResponse delete(ProducerDeleteRequest request)
		throws SessionServiceException, ProducerServiceException;
}
