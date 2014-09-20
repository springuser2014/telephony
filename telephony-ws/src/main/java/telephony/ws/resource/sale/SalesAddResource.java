package telephony.ws.resource.sale;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 */
public interface SalesAddResource {
	
	String URL = "/sales/add";

	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}