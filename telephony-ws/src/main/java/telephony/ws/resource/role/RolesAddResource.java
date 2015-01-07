package telephony.ws.resource.role;

import java.io.IOException;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import telephony.core.service.dto.request.RoleAddRequest;
import telephony.core.service.dto.response.RoleAddResponse;

/**
 * asd.
 */
public interface RolesAddResource {
	
	String URL = "/roles/add";

	/**
	 * asd.
	 * @param entity TODO
	 * @return asd.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	@Post("json")
	RoleAddResponse add(RoleAddRequest entity) throws JSONException, IOException;

}