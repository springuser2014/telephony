package telephony.ws.resource.tax;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.TaxEditRequest;
import telephony.core.service.dto.response.TaxEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface TaxEditResource {

	String URL = "/taxes/edit";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TaxEditResponse edit(TaxEditRequest req);
}
