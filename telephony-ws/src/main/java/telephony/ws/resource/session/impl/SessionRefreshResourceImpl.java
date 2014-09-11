package telephony.ws.resource.session.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.session.SessionRefreshResource;

/**
 * asd.
 */
public class SessionRefreshResourceImpl extends TelephonyServerResource
		implements SessionRefreshResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SessionService sessionService;

	// TODO : use Dto
	
	@Override
	@Put("json")
	public JsonRepresentation refresh(JsonRepresentation entity)
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
			logger.error("Error occured during session refreshing.", e);
		}

		if (session == null) {
			return new JsonRepresentation("Error occured");
		} else {
			Gson gson = new GsonBuilder().create();
			return new JsonRepresentation(gson.toJson(session));
		}
	}

}
