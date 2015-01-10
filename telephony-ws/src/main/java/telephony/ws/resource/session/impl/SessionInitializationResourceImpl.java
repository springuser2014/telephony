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
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.SessionInitializationRequest;
import telephony.core.service.dto.response.SessionInitializationResponse;
import telephony.core.service.exception.SessionServiceException;
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

    @Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public SessionInitializationResponse initialize(SessionInitializationRequest initializationRequest)
    		throws JSONException, IOException {

        logger.info("SessionInitializationResource.initialize");

        SessionInitializationResponse resp = new SessionInitializationResponse();
        SessionDto session = null;

        try {
        	session = sessionService.init(initializationRequest.getUsername(), initializationRequest.getPassword());
        } catch (Exception ex) {
        	logger.error("Error occured during session initialization." , ex);
        }
        
        if (session == null) {
        	getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
            resp.setMessage("internalError");
            resp.setSuccess(false);

            return resp;
        } else {
    		getResponse().setStatus(Status.SUCCESS_OK);

            resp.setMessage("operation performed successfully");
            resp.setSuccess(true);
            resp.setSession(session);
            return resp;
        }
    }
}
