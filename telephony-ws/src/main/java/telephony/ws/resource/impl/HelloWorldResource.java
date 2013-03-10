package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;

// TODO : remove later

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class HelloWorldResource extends ServerResource {

    public static final String URL = "/hello";


    private Logger logger2;

    /**
     * asd.
     */
    public HelloWorldResource() {

    }

    /**
     * asd.
     * @return asd.
     */
    @Get("json")
    public Representation hello() {

        logger2.info("logger 2");

        return new JsonRepresentation("hello world");
    }

}
