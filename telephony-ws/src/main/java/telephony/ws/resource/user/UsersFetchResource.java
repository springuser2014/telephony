package telephony.ws.resource.user;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.UsersFetchRequest;
import telephony.core.service.dto.response.UsersFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UsersFetchResource {

	String URL = "/users/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	UsersFetchResponse fetch(UsersFetchRequest fetchRequest);

}