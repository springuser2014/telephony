package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoreUsersResource extends ServerResource {

    private final Representation rep = new JsonRepresentation("");

    /**
     * asd.
     * @return asd.
     */
    @Get
    public Representation list() {
        return rep;

    }
}
