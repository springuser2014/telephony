package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;

/**
 * asd.
 */
public interface DeliveriesDeleteResource {
	
	String URL = "/deliveries/delete/{id}";

	/**
	 * asd. 
	 * @param entity asd.
	 * @return asd.
	 */
	@Delete("json")
	JsonRepresentation delete(JsonRepresentation entity);

}