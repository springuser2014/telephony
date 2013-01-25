package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

public class StoresResource extends ServerResource {

    private Representation rep = new JsonRepresentation("");

    @Get
    public Representation list() {
        return rep;

    }

    @Put
    public Representation edit() {
        return rep;

    }

    @Post
    public Representation add() {
        return rep;

    }

    @Delete
    public Representation delete() {
        return rep;

    }
}
