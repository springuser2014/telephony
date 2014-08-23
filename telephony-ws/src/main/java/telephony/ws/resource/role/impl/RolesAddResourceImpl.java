package telephony.ws.resource.role.impl;


import java.io.IOException;
import java.util.HashSet;

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
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.bean.BasicResponse;
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
    public JsonRepresentation add(JsonRepresentation entity) 
    		throws JSONException, IOException {
    	
    	logger.info("RolesAddResource.add starts");
    	JSONObject req = new JsonRepresentation(entity.getText()).getJsonObject();
    	
    	String username = req.getString("username");
    	String sessionId = req.getString("sessionId");
    	String label = req.getString("label");
    	
    	Role newrole = new Role();
    	newrole.setName(label);
    	newrole.setStore(new HashSet<Store>());
    	newrole.setUsers(new HashSet<User>());
    	
    	BasicResponse response = new BasicResponse(true, "Dodano sukcesywnie");
    	try {
    		roleService.add(null, newrole);
    	} catch (Exception ex) {
    		logger.error(ex.getMessage());
    		logger.error(ex.toString());
    		response.setMessage("Wystąpił błąd podczas dodawania");
    		response.setSuccess(false);
    		return new JsonRepresentation(response);
    	}
    	
        return new JsonRepresentation(response);
    }


}
