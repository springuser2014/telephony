package telephony.ws.resource.sale;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.SaleDeleteRequest;
import telephony.core.service.dto.response.SaleDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SalesDeleteResource {

	String URL = "/sales/delete";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	SaleDeleteResponse delete(SaleDeleteRequest deleteRequest);

}
