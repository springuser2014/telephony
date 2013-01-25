package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

public class UserStoresResource extends ServerResource {

    private Representation representation = new JsonRepresentation("");

    @Put
    public Representation set() {
        return representation;
    }
}
