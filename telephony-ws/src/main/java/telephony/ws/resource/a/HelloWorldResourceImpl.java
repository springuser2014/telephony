package telephony.ws.resource.a;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.HelloWorldResource;
import telephony.ws.resource.TelephonyServerResource;

// TODO : remove later

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
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
