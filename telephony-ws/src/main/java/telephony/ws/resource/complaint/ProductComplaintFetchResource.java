package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.response.ComplaintFetchResponse;
import telephony.core.service.dto.response.ProductComplaintFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductComplaintFetchResource {

	String URL = "/product/complaint/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ProductComplaintFetchResponse fetch(ProductComplaintFetchRequest request);
}
