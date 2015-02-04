package telephony.ws.resource.user;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.UserChangePasswordRequest;
import telephony.core.service.dto.response.UserChangePasswordResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface UsersChangePasswordResource {

    String URL = "/users/changePassword";

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    UserChangePasswordResponse changePassword(UserChangePasswordRequest request);
}
