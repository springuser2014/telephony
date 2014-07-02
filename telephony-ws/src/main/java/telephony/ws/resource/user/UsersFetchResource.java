package telephony.ws.resource.user;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 */
public interface UsersFetchResource {

	String URL = "/users/fetch";

	/**
	 * asd.
	 * @para entity .asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation fetch(JsonRepresentation entity);

}