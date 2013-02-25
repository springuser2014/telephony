package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import telephony.ws.AnyBean;

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
    private final Representation representation = new JsonRepresentation("hello");

    private final AnyBean bean = new AnyBean();

    private final telephony.core.AnyBean bean2 = new telephony.core.AnyBean();

       /**
     * Some text.
     * @return asd
     */
    @Get("json")
    public final Representation list() {
        bean.setName("Pawełek ");
        bean2.setMail("majl");

        return new JsonRepresentation("pawel henek : witajcie!," + bean.getName() + bean2.getMail());
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
    @Override
    @Delete
    public final Representation delete() {
        return representation;
    }
}
