package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionInitializationRequest;
import telephony.core.service.dto.response.SessionInitializationResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SessionInitializationResource  {
	
	String URL = "/session/initialize";

	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SessionInitializationResponse initialize(SessionInitializationRequest initializationRequest);

}
