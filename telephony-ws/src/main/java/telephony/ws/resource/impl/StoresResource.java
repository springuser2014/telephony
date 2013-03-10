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
public class StoresResource extends ServerResource {

    private final Representation rep = new JsonRepresentation("");

    /**
     * asd.
     * @return asd.
     */
    @Get
    public Representation list() {
        return rep;

    }

    /**
     * asd.
     * @return asd.
     */
    @Put
    public Representation edit() {
        return rep;

    }

    /**
     * asd.
     * @return asd.
     */
    @Post
    public Representation add() {
        return rep;

    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    @Delete
    public Representation delete() {
        return rep;

    }
}
