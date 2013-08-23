package telephony.ws.resource.impl;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.Config;
import telephony.ws.guice.env.TelephonyWebServicesProductionModule;
import telephony.ws.resource.SessionResource;
import telephony.ws.resource.TelephonyServerResource;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SessionResourceImpl extends TelephonyServerResource 
	implements SessionResource {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private SessionService sessionService;
    

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     * @throws JSONException asd.
     * @throws IOException asd.
     */
    @Post("json")
    public JsonRepresentation start(Representation entity) 
    		throws JSONException, IOException {

        logger.info("startSession starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String password = req.getString("password");

        logger.info(" username = {} ", name);
        logger.info(" password = {} ", password);
        Session session = null;
        try {
        	session = sessionService.init(name, password);	
        } catch (Exception e) {
        	logger.info("Error occured during session initialization." , e);
        }

        if (session == null) {
            return new JsonRepresentation("Error occured");
        } else {
            return new JsonRepresentation(session);
        }
    }

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     * @throws IOException asd.
     * @throws JSONException asd.
     */
    @Delete("json")
    public JsonRepresentation end(Representation entity)
    		throws IOException, JSONException {

        logger.info("endSession starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String sessionId = req.getString("sessionId");

        logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);
        
        boolean success = false;
        try {
        	success = sessionService.destroy(null);
        } catch (SessionServiceException e) { 
        	logger.info("Error occured during session ending.", e);
        }

        HashMap<String, String> res = new HashMap<String, String>();
        res.put("success", new Boolean(success).toString());

        return new JsonRepresentation(res);
    }

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     * @throws IOException asd.
     * @throws JSONException asd.
     */
    @Put("json")
    public JsonRepresentation refresh(Representation entity)
    		throws IOException, JSONException {

        logger.info("refresh starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String sessionId = req.getString("sessionId");
        Session sessionToRefresh = Session.create(name, sessionId);
        
        logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);
        Session session = null;
        
        try {
        	session = sessionService.refresh(sessionToRefresh);
        } catch (SessionServiceException e) { 
        	logger.info("Error occured during session refreshing.", e);
        }        

        if (session == null) {
			return new JsonRepresentation("Error occured");
		} else {
			return new JsonRepresentation(session);
		}
    }

	

}
