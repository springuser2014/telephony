package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionRefreshRequest;
import telephony.core.service.dto.response.SessionRefreshResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SessionRefreshResource {

	String URL = "/session/refresh";

	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SessionRefreshResponse refresh(SessionRefreshRequest refreshRequest);

}