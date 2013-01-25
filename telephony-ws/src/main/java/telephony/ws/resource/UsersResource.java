package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class UsersResource extends ServerResource {

    private Representation representation = new JsonRepresentation("");

    @Get
    public Representation list() {
        return representation;
    }

    @Post
    public Representation addNew() {
        return representation;
    }

    public Representation edit() {
        return representation;
    }

    @Delete
    public Representation delete() {
        return representation;
    }
}
