package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductComplaintDetailsFetchRequest;
import telephony.core.service.dto.response.ProductComplaintDetailsFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductComplaintDetailsResource {

	String URL = "/product/complaint/details";

	@Post("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	ProductComplaintDetailsFetchResponse details(ProductComplaintDetailsFetchRequest request);
	
}
