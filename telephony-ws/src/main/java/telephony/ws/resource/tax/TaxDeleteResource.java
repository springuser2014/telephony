package telephony.ws.resource.tax;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.response.TaxDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface TaxDeleteResource {

	String URL = "/taxes/delete";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TaxDeleteResponse delete(TaxDeleteRequest req);
}
