package telephony.ws.resource.session;

import org.json.JSONException;
import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionRefreshRequest;
import telephony.core.service.dto.response.SessionRefreshResponse;

import java.io.IOException;

public interface SessionRefreshResource {

	String URL = "/session/refresh";

	@Post("json")
	SessionRefreshResponse refresh(SessionRefreshRequest refreshRequest)
			throws IOException, JSONException;

}