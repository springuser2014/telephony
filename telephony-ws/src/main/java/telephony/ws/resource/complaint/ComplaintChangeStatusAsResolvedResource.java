package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ComplaintChangeStatusRequest;
import telephony.core.service.dto.response.ComplaintChangeStatusResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ComplaintChangeStatusAsResolvedResource {

    String URL = "/complaint/markAsResolved";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ComplaintChangeStatusResponse markAsResolved(ComplaintChangeStatusRequest request);

}
