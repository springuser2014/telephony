package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionValidationRequest;
import telephony.core.service.dto.response.SessionValidationResponse;

public interface SessionValidationResource {

	String URL = "/session/validate";

	@Post("json")
	SessionValidationResponse validate(SessionValidationRequest validationRequest);

}