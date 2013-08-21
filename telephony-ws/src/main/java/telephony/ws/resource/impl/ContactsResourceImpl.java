package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class ContactsResourceImpl extends ServerResource {

    /**
     * asd.
	 */
    private final Representation rep = new JsonRepresentation("");

	/**
	 * asd.
	 *
	 * @return asd.
	 */
    @Override
	@Get
	public final Representation get() {
        return rep;
    }

	/**
	 * asd.
	 * @return asd.
	 */
    @Post
	public final Representation post() {
        return rep;
    }

	/**
	 * asd.
	 * @return asd.
	 */
    @Override
	@Delete
	public final Representation delete() {
        return rep;
    }
}
