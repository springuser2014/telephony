package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Added some stupid comment.
 * @author gam3r
 *
 */
public class UsersResource extends ServerResource {

    /**
     * Some text.
     */
    public static final String URL = "/users";

    /**
     * Some text.
     */
    private Representation representation = new JsonRepresentation("hello");

    /**
     * Some text.
     * @return asd
     */
    @Get("json")
    public final Representation list() {
        return new JsonRepresentation("pawel henek witaj");
    }

    /**
     * Some text.
     * @return asd
     */
    @Post
    public final Representation addNew() {
        return representation;
    }

    /**
     * Some text.
     * @return asd
     */
    public final Representation edit() {
        return representation;
    }

    /**
     * Some text.
     * @return asd
     *
     */
    @Delete
    public final Representation delete() {
        return representation;
    }
}
