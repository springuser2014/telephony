package telephony.ws.resource.store;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.response.StoreDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface StoresDeleteResource {
	
	String URL = "/stores/delete/{id}";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	StoreDeleteResponse delete(StoreDeleteRequest deleteRequest);

}