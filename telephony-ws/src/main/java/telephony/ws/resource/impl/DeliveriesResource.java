package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesResource extends ServerResource {

    private final Representation representation = new JsonRepresentation("");

    /**
     * asd.
     * @return asd.
     */
    @Override
    @Get
    public Representation get() {
        return representation;
    }


    /**
     * asd.
     * @return asd.
     */
    @Put
    public Representation put() {
        return representation;
    }

    /**
     * asd.
     * @return asd.
     */
    @Post
    public Representation post() {
        return representation;
    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    @Delete
    public Representation delete() {
        return representation;
    }

}
