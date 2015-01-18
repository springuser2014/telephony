package telephony.ws.resource.sale;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SaleAddRequest;
import telephony.core.service.dto.response.SaleAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SalesAddResource {
	
	String URL = "/sales/add";

	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	SaleAddResponse add(SaleAddRequest addRequest);
}