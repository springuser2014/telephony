package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;

// TODO : remove later

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class HelloWorldResource extends TelephonyServerResource {

    public static final String URL = "/hello";


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @return asd.
     */
    @Get("json")
    public Representation hello() {

    	logger.info("helloworld resource");

        return new JsonRepresentation("hello world");
    }

}
