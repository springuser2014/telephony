package telephony.ws.resource.contact;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ContactsAddResource {
	
	String URL = "/contacts/add";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation add(JsonRepresentation entity);

}