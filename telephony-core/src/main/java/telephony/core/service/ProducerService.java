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

	////////////////////////////////
	// TODO remove the stuff below
	////////////////////////////////

	/**
	 * ads.
	 * @param session TODO
	 * @param label a.
	 * @return a.
	 */
	@Deprecated
	Producer findByLabel(SessionDto session, String label);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return a.
	 */
	@Deprecated
	Producer findById(SessionDto session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return d.
	 */
	@Deprecated
	Collection<Producer> findById(SessionDto session, Collection<Long> ids);

	/**
	 * ads.
	 * @param session TODO
	 * @param producer a.
	 * @return asd.
	 */
	@Deprecated
	Producer update(SessionDto session, Producer producer);

	/**
	 * asd.
	 * @param session TODO
	 * @param producers a.
	 * @return a.
	 */
	@Deprecated
	Collection<Producer> update(SessionDto session, Collection<Producer> producers);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 */
	@Deprecated
	void removeById(SessionDto session, Long id);
	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids asd.
	 */
	@Deprecated
	void removeById(SessionDto session, Collection<Long> ids);
}
