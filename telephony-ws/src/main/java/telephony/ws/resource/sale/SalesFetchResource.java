package telephony.ws.resource.sale;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SalesFetchRequest;
import telephony.core.service.dto.response.SalesFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SalesFetchResource {
	
	String URL = "/sales/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	SalesFetchResponse fetch(SalesFetchRequest fetchRequest);
}