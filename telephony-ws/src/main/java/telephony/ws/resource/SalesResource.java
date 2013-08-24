package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SalesResource {
	
	String URL = "/sales";

	/**
	 * {@inheritDoc}
	 */
	@Get("json")
	JsonRepresentation gest(JsonRepresentation entity);

	/**
	 * {@inheritDoc}
	 */
	@Post("json")
	JsonRepresentation post(JsonRepresentation entity);

}