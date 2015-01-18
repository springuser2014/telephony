package telephony.ws.resource.products;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.ProductFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductsFetchResource {

	String URL = "/products/fetch";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ProductFetchResponse fetch(ProductFetchRequest request);

}
