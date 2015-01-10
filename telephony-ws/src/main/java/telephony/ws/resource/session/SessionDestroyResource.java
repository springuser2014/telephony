package telephony.ws.resource.session;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.SessionDestroyRequest;
import telephony.core.service.dto.response.SessionDestroyResponse;

public interface SessionDestroyResource {
	
	String URL = "/session/destroy";

	@Delete("json")
	SessionDestroyResponse destroy(SessionDestroyRequest destroyRequest);

}