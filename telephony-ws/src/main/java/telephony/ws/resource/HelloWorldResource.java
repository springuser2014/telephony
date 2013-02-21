/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.guice.Log;

// TODO : remove later

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class HelloWorldResource extends ServerResource {

    public static final String URL = "/hello";

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Log
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

        //logger2.info("logger 2");

        return new JsonRepresentation("hello world");
    }

}
