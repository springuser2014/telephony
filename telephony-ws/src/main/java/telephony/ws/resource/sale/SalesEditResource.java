package telephony.ws.resource.sale;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 */
public interface SalesEditResource {

	String URL = "/sales/edit/{id}";

	/**
	 * {@inheritDoc}
	 */
	@Put("json")
	JsonRepresentation edit(JsonRepresentation entity);

}