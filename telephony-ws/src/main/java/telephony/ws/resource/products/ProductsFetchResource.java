package telephony.ws.resource.products;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

import telephony.core.service.dto.ProductFetchRequest;
import telephony.core.service.dto.ProductFetchResponse;

public interface ProductsFetchResource {

	String URL = "/products/fetch";
	
	@Post("json")
	JsonRepresentation fetch(ProductFetchRequest request);
}
