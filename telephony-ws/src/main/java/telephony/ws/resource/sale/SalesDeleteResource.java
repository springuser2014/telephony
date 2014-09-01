package telephony.ws.resource.sale;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 */
public interface SalesDeleteResource {

	String URL = "/sales/delete/{id}";

	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}
