package telephony.ws.resource.delivery;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.DeliveriesFetchRequest;
import telephony.core.service.dto.response.DeliveriesFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DeliveriesFetchResource {
	
	String URL = "/deliveries/fetch";
	
	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	DeliveriesFetchResponse fetch(DeliveriesFetchRequest fetchRequest);

}
