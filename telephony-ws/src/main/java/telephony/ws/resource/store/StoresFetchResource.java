package telephony.ws.resource.store;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface StoresFetchResource {
	
	String URL = "/stores/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	StoreFetchResponse fetch(StoreFetchRequest fetchRequest);

}