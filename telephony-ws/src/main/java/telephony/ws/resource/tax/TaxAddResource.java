package telephony.ws.resource.tax;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.response.TaxAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface TaxAddResource {

	String URL = "/taxes/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TaxAddResponse add(TaxAddRequest req);
}
