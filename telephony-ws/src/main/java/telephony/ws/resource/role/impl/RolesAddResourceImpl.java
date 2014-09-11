package telephony.ws.resource.role.impl;


import java.io.IOException;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.RoleService;
import telephony.core.service.dto.BasicResponse;
import telephony.core.service.dto.Session;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.role.RolesAddResource;

import com.google.inject.Inject;

/**
 * asd.
 */
public class RolesAddResourceImpl extends TelephonyServerResource
	implements RolesAddResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private RoleService roleService;

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation add(JsonRepresentation entity) 
    		throws JSONException, IOException {
    	
    	logger.info("RolesAddResource.add starts");
    	JSONObject req = new JsonRepresentation(entity.getText()).getJsonObject();
    	
    	String username = req.getString("username");
    	String sessionId = req.getString("sessionId");
    	String label = req.getString("label");
    	
    	Role newrole = new Role();
    	newrole.setName(label);
    	newrole.setUsers(new HashSet<User>());
    	
    	Session session = Session.create()
    						.setUsername(username)
    						.setSessionId(sessionId);
    	
    	BasicResponse response = new BasicResponse(true, "Added successfully");
    	try {
    		roleService.add(session, newrole);
    	} catch (Exception ex) {
    		logger.error(ex.getMessage());
    		logger.error(ex.toString());
    		response.setMessage("An error occured during");
    		response.setSuccess(false);
    		return new JsonRepresentation(response);
    	}
    	
        return new JsonRepresentation(response);
    }


}
