package telephony.ws.resource.sale;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;

/**
 * asd.
 */
public interface SalesFetchResource {
	
	String URL = "/sales/fetch";

	/**
	 * {@inheritDoc}
	 */
	@Get("json")
	JsonRepresentation fetch(JsonRepresentation entity);

}