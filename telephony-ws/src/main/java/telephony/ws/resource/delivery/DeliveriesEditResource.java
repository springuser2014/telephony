package telephony.ws.resource.delivery;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.DeliveryEditRequest;
import telephony.core.service.dto.response.DeliveryEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface DeliveriesEditResource  {

	String URL = "/deliveries/edit";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	DeliveryEditResponse edit(DeliveryEditRequest editRequest);
	
}
