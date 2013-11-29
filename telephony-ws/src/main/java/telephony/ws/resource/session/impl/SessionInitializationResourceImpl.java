package telephony.ws.resource.session.impl;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.engine.header.Header;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.Config;
import telephony.ws.guice.env.TelephonyWebServicesProductionModule;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionInitializationResource;

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
public class SessionInitializationResourceImpl extends TelephonyServerResource 
	implements SessionInitializationResource {    

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	/**
	 * {@inheritDoc}
	 */
    @Post("json")
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
            return new JsonRepresentation(session);
        }
    }
}
