package telephony.ws.resource.user;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.UserEditRequest;
import telephony.core.service.dto.response.UserEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UsersEditResource {
	
	String URL = "/users/edit";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	UserEditResponse edit(UserEditRequest editRequest);
}
