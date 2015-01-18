package telephony.ws.resource.sale;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.SaleEditRequest;
import telephony.core.service.dto.response.SaleEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SalesEditResource {

	String URL = "/sales/edit";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	SaleEditResponse edit(SaleEditRequest editRequest);

}