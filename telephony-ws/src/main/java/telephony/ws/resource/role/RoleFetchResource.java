package telephony.ws.resource.role;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.RoleFetchRequest;
import telephony.core.service.dto.response.RoleFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface RoleFetchResource {

    String URL = "/roles/fetch";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    RoleFetchResponse fetch(RoleFetchRequest fetchRequest);
}
