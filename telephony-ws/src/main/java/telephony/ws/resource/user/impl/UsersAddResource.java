package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UsersAddResource {

	String URL = "/users/add";

	/**
	 * {@inheritDoc}
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}