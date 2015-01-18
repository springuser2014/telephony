package telephony.ws.resource.products;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.response.ProductDetailsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductsDetailsResource {

	String URL = "/products/details";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ProductDetailsResponse details(ProductDetailsRequest request);
}
