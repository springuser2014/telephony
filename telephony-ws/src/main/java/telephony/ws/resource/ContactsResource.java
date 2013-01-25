package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class ContactsResource extends ServerResource {

    private Representation rep = new JsonRepresentation("");

    @Get
    public Representation get() {
        return rep;
    }

    @Post
    public Representation post() {
        return rep;
    }

    @Delete
    public Representation delete() {
        return rep;
    }
}
