package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionValidationRequest;
import telephony.core.service.dto.response.SessionValidationResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SessionValidationResource {

	String URL = "/session/validate";

	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SessionValidationResponse validate(SessionValidationRequest validationRequest);

}