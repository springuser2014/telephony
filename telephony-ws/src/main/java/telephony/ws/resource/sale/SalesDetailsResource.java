package telephony.ws.resource.sale;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SaleDetailsRequest;
import telephony.core.service.dto.response.SaleDetailsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SalesDetailsResource {

	String URL = "/sales/details";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	SaleDetailsResponse details(SaleDetailsRequest request);
}
