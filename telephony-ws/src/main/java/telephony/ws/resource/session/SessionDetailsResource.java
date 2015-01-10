package telephony.ws.resource.session;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;

public interface SessionDetailsResource {

    String URL = "/session/details";

    @Post("json")
    SessionDetailsResponse details(SessionDetailsRequest request);
}
