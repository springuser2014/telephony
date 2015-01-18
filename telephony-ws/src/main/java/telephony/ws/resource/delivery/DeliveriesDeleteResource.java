package telephony.ws.resource.delivery;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.DeliveryDeleteRequest;
import telephony.core.service.dto.response.DeliveryDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DeliveriesDeleteResource {
	
	String URL = "/deliveries/delete";

	@Delete("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	DeliveryDeleteResponse delete(DeliveryDeleteRequest deleteRequest);

}