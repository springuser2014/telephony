package telephony.ws.resource.session.impl;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionInitializationResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

/**
 * asd.
 */
public class SessionInitializationResourceImpl 
extends TelephonyServerResource 
implements SessionInitializationResource {    

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	//TODO : use Dto
	
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation initialize(JsonRepresentation entity) 
    		throws JSONException, IOException {

        logger.info("SessionInitializationResource.initialize");
        
        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String password = req.getString("password");
        
        logger.info(" username = {} ", name);
        logger.info(" password = {} ", password);
        Session session = null;
        try {
        	session = sessionService.init(name, password);	
        } catch (Exception e) {
        	logger.error("Error occured during session initialization." , e);
        }
        
        if (session == null) {
        	getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return new JsonRepresentation("Error occured");
        } else {
    		getResponse().setStatus(Status.SUCCESS_OK);
    		Gson gson = new GsonBuilder().create();    		
            return new JsonRepresentation(gson.toJson(session));
        }
    }
}
