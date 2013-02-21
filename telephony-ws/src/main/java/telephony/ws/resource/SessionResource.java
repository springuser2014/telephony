package telephony.ws.resource;

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
import telephony.core.service.bean.Session;
import telephony.core.service.interfaces.SessionService;
import telephony.ws.Config;

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
public class SessionResource extends ServerResource {

    public static final String URL = "/session";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private SessionService sessionService;

    @SuppressWarnings("unused")
    private final Representation representation = new JsonRepresentation("");

    /**
     * asd.
     */
    public SessionResource() {
        Injector inj = Guice.createInjector(
                new JpaPersistModule(Config.PERSISTENCE),
                new TelephonyCoreServicesModule()
        );

        inj.injectMembers(this);
    }

    /**
     * asd.
     * @param persistService asd.
     */
    @Inject
	protected final void init(final PersistService persistService) {
        persistService.start();
    }

    /**
     * asd.
     * @param entity asd.
     * @return asd.
     * @throws JSONException asd.
     * @throws IOException asd.
     */
    @Post("json")
    public Representation startSession(Representation entity) throws JSONException, IOException {

        logger.info("startSession starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String password = req.getString("password");

        logger.info(" username = {} ", name);
        logger.info(" password = {} ", password);

        Session session = sessionService.init(name, password);



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
    public Representation endSession(Representation entity) throws IOException, JSONException {

        logger.info("endSession starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String sessionId = req.getString("sessionId");

        logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);

        boolean success = sessionService.destroy(name, sessionId);

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
    public Representation refresh(Representation entity)
    		throws IOException, JSONException {

        logger.info("refresh starts");

        JSONObject req = new JsonRepresentation(entity).getJsonObject();
        String name = req.getString("username");
        String sessionId = req.getString("sessionId");

        logger.info(" username = {} ", name);
        logger.info(" sessionId = {} ", sessionId);

        Session session = sessionService.refresh(name, sessionId);

        if (session == null) {
			return new JsonRepresentation("Error occured");
		} else {
			return new JsonRepresentation(session);
		}
    }

}
