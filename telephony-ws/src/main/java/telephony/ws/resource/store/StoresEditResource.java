package telephony.ws.resource.store;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.response.StoreEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface StoresEditResource {
	
	String URL = "/stores/edit/{id}";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	StoreEditResponse edit(StoreEditRequest editRequest);

}