package telephony.ws.resource.role.impl;

import java.io.IOException;
import java.util.HashSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.entity.jpa.Role;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.RoleService;
import telephony.core.service.dto.BasicResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.role.RolesDeleteResource;

/**
 * asd.
 */
public class RolesDeleteResourceImpl 
extends TelephonyServerResource 
implements RolesDeleteResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private RoleService roleService;

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation delete(JsonRepresentation entity) 
			throws JSONException, IOException {
		
		logger.info("RolesAddResource.add starts");
    	JSONObject req = new JsonRepresentation(entity.getText()).getJsonObject();
    	
    	String username = req.getString("username");
    	String sessionId = req.getString("sessionId");
    	String label = req.getString("label");
    	
    	BasicResponse response = new BasicResponse(true, "Usunięto obiekt");
    	try {
    		// TODO: add to one @transactional method
    		Role roleToDelete = roleService.findByLabel(null, label);
    		roleService.remove(null, roleToDelete);
    		
    	} catch (Exception ex) {
    		logger.error(ex.getMessage());
    		logger.error(ex.toString());
    		response.setMessage("Wystąpił błąd podczas usuwania");
    		response.setSuccess(false);
    		return new JsonRepresentation(response);
    	}
    	
        return new JsonRepresentation(response);    
	}

}
