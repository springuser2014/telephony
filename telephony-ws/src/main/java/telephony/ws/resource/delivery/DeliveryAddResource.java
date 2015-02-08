package telephony.ws.resource.delivery;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.DeliveryAddRequest;
import telephony.core.service.dto.response.DeliveryAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DeliveryAddResource {

	String URL = "/deliveries/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	DeliveryAddResponse add(DeliveryAddRequest addRequest);
}
