
package telephony.ws.resource.session.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SessionService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionValidationResource;

/**
 * asd.
 */
public class SessionValidationResourceImpl extends TelephonyServerResource
		implements SessionValidationResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	@Override
	@Post("json")
	public JsonRepresentation validate(JsonRepresentation entity) 
			throws IOException, JSONException {

		logger.info("validation starts");

		JSONObject req = entity.getJsonObject();
		String name = req.getString("username");
		String sessionId = req.getString("sessionId");
		SessionDto sessionToValidate = SessionDto.create(name, sessionId);

		logger.debug(" username = {} ", name);
		logger.debug(" sessionId = {} ", sessionId);
		Boolean isValid = null;
		try {
			isValid = new Boolean(sessionService.validate(sessionToValidate));
		} catch (SessionServiceException e) {
			logger.error("Error occured during session validation " + name + " " + sessionId, e);
		}

		if (isValid == null) {
			// getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);
			return new JsonRepresentation("Error occured");
		} else if (isValid.booleanValue()) {
			return new JsonRepresentation("true");
		} else {
			return new JsonRepresentation("false");
		}
	}


}
