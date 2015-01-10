package telephony.ws.resource.session;

import org.json.JSONException;
import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionInitializationRequest;
import telephony.core.service.dto.response.SessionInitializationResponse;

import java.io.IOException;

public interface SessionInitializationResource  {
	
	String URL = "/session/initialize";

	@Post("json")
	SessionInitializationResponse initialize(SessionInitializationRequest initializationRequest)
			throws JSONException, IOException;

}
