package telephony.core.service;

import telephony.core.entity.jpa.Model;
import telephony.core.service.dto.request.ModelDeleteRequest;
import telephony.core.service.dto.request.ModelEditRequest;
import telephony.core.service.dto.request.ModelFetchRequest;
import telephony.core.service.dto.response.ModelDeleteResponse;
import telephony.core.service.dto.response.ModelEditResponse;
import telephony.core.service.dto.response.ModelFetchResponse;

/**
 * asd.
 */
public interface ModelService extends BasicService<Model> {
	

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	ModelFetchResponse fetch(ModelFetchRequest request);

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	ModelEditResponse edit(ModelEditRequest request);

	/**
	 * asd.
	 * @param request a.
	 * @return a.
	 */
	ModelDeleteResponse delete(ModelDeleteRequest request);
}
