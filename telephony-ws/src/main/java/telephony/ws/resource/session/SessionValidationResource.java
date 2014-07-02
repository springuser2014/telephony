package telephony.ws.resource.session;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;

/**
 * asd.
 */
public interface SessionValidationResource {

	String URL = "/session/validate";

	/**
	 * asd.
	 * @param entity asd.
	 * @return asd.
	 * @throws IOException asd.
	 * @throws JSONException asd.
	 */
	@Post("json")
	JsonRepresentation validate(JsonRepresentation entity) throws IOException, JSONException;

}