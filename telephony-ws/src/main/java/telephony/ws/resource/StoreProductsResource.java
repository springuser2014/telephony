package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface StoreProductsResource {

	String URL = "/store/products/{id}";
	
	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation list(JsonRepresentation entity);

}