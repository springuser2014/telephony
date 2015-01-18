package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SessionDetailsResource {

    String URL = "/session/details";

    @Post("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    SessionDetailsResponse details(SessionDetailsRequest request);

}
