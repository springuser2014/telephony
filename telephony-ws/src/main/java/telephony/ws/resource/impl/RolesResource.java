package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface RolesResource {
	
	String URL = "/roles";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 */
	@Put("json")
	Representation set(JsonRepresentation entity);

}