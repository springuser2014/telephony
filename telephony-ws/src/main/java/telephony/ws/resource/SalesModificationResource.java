package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SalesModificationResource {

	String URL = "/sales/{id}";

	/**
	 * {@inheritDoc}
	 */
	@Put("json")
	JsonRepresentation put(JsonRepresentation entity);

	/**
	 * {@inheritDoc}
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}