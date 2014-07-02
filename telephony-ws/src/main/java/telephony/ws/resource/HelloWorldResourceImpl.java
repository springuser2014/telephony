package telephony.ws.resource;

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

    /* (non-Javadoc)
	 * @see telephony.ws.resource.impl.HelloWorldResource#hello()
	 */
    @Override
	@Get("json")
    public JsonRepresentation hello() {

    	logger.info("helloworld resource");

        return new JsonRepresentation("hello world");
    }

}
