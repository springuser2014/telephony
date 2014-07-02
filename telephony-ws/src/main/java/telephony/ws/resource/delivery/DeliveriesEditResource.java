package telephony.ws.resource.delivery;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;

/**
 * asd.
 */
public interface DeliveriesEditResource  {

	String URL = "/deliveries/edit/{id}";
	
	/**
	 * asd.
	 * @param entity asd.
 	 * @return asd.
	 */
	@Put("json")
	JsonRepresentation edit(JsonRepresentation entity);
	
}
