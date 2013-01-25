package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

public class SalesResource extends ServerResource {

    private Representation rep = new JsonRepresentation("");

    @Get
    public Representation get() {
        return rep;
    }

    @Post
    public Representation post() {
        return rep;
    }

    @Put
    public Representation put() {
        return rep;
    }

    @Delete
    public Representation delete() {
        return rep;
    }
}
