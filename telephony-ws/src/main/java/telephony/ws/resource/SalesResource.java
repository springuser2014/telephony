package telephony.ws.resource;

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
public class SalesResource extends ServerResource {

    private final Representation rep = new JsonRepresentation("");

    /**
     * asd.
     * @return asd.
     */
    @Get
    public Representation gest() {
        return rep;
    }

    /**
     * asd.
     * @return asd.
     */
    @Post
    public Representation post() {
        return rep;
    }

    /**
     * asd.
     * @return asd.
     */
    @Put
    public Representation put() {
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
