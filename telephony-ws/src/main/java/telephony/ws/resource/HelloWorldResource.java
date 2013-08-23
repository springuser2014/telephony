package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface HelloWorldResource {
	
	/**
	 * asd.
	 */
	String URL = "/hello";

	/**
	 * asd.
	 * @return asd.
	 */
	@Get("json")
	JsonRepresentation hello();

}