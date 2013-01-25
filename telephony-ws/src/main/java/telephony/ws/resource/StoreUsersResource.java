package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class StoreUsersResource extends ServerResource {

    private Representation rep = new JsonRepresentation("");

    @Get
    public Representation list() {
        return rep;

    }
}
