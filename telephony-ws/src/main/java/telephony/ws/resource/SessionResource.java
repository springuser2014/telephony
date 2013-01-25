package telephony.ws.resource;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
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
import telephony.core.service.Session;
import telephony.core.service.interfaces.SessionService;
import telephony.ws.Config;

import java.io.IOException;

/**
 * @todo Implement
 */
public class SessionResource extends ServerResource {

    public static final String URL = "/session";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private SessionService sessionService;

    private Representation representation = new JsonRepresentation("");

    public SessionResource() {
        Injector inj = Guice.createInjector(
                new JpaPersistModule(Config.PERSISTENCE),
                new TelephonyCoreServicesModule()
        );

        inj.injectMembers(this);
    }

    @Inject
    protected void init(PersistService persistService) {
        persistService.start();
    }

    @Post("json")
    public Representation startSession(Representation entity) throws JSONException, IOException {

        logger.info("startSession starts");

        JSONObject req = (new JsonRepresentation(entity)).getJsonObject();
        String name = req.getString("username");
        String password = req.getString("password");

        logger.info(" entity = {} ", entity.toString());
        logger.info(" username = {} ", name);
        logger.info(" password = {} ", password);

        Session session = sessionService.init(name, password);

        if (session == null)
            return new JsonRepresentation("Error occured");
        else
            return new JsonRepresentation("SUcceess");
    }

    @Delete
    public Representation endSession() {
        return representation;
    }

    @Put
    public Representation refresh() {
        return representation;
    }


}
