package telephony.ws.resource.contact;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * ads.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface ContactsFetchResource {

	String URL = "/contacts/fetch";
	
	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 */
	@Post("json")
	JsonRepresentation fetch(JsonRepresentation entity);

}