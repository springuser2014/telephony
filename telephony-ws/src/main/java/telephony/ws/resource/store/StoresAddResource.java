package telephony.ws.resource.store;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.response.StoreAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface StoresAddResource {
	
	String URL = "/stores/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	StoreAddResponse add(StoreAddRequest addRequest);

}