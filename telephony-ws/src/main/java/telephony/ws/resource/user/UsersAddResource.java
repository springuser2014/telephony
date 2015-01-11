package telephony.ws.resource.user;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.UserAddRequest;
import telephony.core.service.dto.response.UserAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UsersAddResource {

	String URL = "/users/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	UserAddResponse add(UserAddRequest addRequest);

}