package telephony.ws.resource.complaint;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.ComplaintDeleteRequest;
import telephony.core.service.dto.response.ComplaintDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ComplaintDeleteResource {

	String URL = "/complaint/delete";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ComplaintDeleteResponse delete(ComplaintDeleteRequest request);
}
