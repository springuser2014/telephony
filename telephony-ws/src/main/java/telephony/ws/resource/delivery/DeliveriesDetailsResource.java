package telephony.ws.resource.delivery;

import org.restlet.resource.Post;
import org.restlet.resource.Put;
import telephony.core.service.dto.request.DeliveryDetailsRequest;
import telephony.core.service.dto.response.DeliveryDetailsResponse;
import telephony.core.service.exception.SessionServiceException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DeliveriesDetailsResource {

	String URL = "/deliveries/details";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	DeliveryDetailsResponse details(DeliveryDetailsRequest request);
}
