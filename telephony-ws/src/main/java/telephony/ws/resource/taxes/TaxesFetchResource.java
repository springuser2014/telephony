package telephony.ws.resource.taxes;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface TaxesFetchResource {

	String URL = "/taxes/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	TaxFetchResponse fetch(TaxFetchRequest req);

}
