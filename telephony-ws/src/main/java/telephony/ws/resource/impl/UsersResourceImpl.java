package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Added some stupid comment.
 * @author gam3r
 *
 */
public class UsersResourceImpl extends ServerResource  {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Some text.
     */
    public static final String URL = "/users";

    /**
     * Some text.
     */
    private final Representation representation = new JsonRepresentation("hello");

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


    /**
     * asd.
     * @return asd.
     */
    @Get
    public Representation list() {

        logger.info("UsersResource.get method");

        return new JsonRepresentation("efbgb!!");
    }
}
