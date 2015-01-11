package telephony.ws.resource.store;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 */
@Deprecated
public interface StoreFetchProductsResource {

	String URL = "/store/fetchProducts/{id}";
	
	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation list(JsonRepresentation entity);

}