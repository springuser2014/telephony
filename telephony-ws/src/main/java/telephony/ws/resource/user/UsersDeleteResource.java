package telephony.ws.resource.user;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.UserDeleteRequest;
import telephony.core.service.dto.response.UserDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UsersDeleteResource {

	String URL = "/users/delete";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	UserDeleteResponse delete(UserDeleteRequest deleteRequest);
}