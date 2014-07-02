package telephony.ws.resource.session.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionDestroyResource;

/**
 * asd.
 */
public class SessionDestoryResourceImpl extends TelephonyServerResource
		implements SessionDestroyResource {
	
	 private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	/**
	 * {@inheritDoc}
	 */
	@Override	
    @Delete("json")
    public JsonRepresentation destroy(JsonRepresentation entity)
    		throws IOException, JSONException {

        logger.info("endSession starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String username = req.getString("username");
        String sessionId = req.getString("sessionId");

        logger.info(" username = {} ", username);
        logger.info(" sessionId = {} ", sessionId);
        
        Session session = Session.create(username, sessionId);
        
        boolean success = false;
        try {
        	success = sessionService.destroy(session);
        } catch (SessionServiceException e) { 
        	logger.error("Error occured during session ending.", e);
        }
        return new JsonRepresentation(success);
    }
}
