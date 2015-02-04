package telephony.ws.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// TODO : remove later

/**
 * asd.
 */
public class HelloWorldResourceImpl extends TelephonyServerResource implements HelloWorldResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
	@Get("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation hello() {

    	logger.info("helloworld resource");

        return new JsonRepresentation("hello1 world");
    }

}
