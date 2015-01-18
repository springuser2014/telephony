package telephony.ws.resource.session;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.SessionDestroyRequest;
import telephony.core.service.dto.response.SessionDestroyResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SessionDestroyResource {
	
	String URL = "/session/destroy";

	@Delete("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SessionDestroyResponse destroy(SessionDestroyRequest destroyRequest);

}