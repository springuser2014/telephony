package telephony.ws.resource;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.*;

public class DeliveriesResource extends ServerResource {

    private Representation representation = new JsonRepresentation("");

    @Get
    public Representation get() {
        return representation;
    }

    @Put
    public Representation put() {
        return representation;
    }

    @Post
    public Representation post() {
        return representation;
    }

    @Delete
    public Representation delete() {
        return representation;
    }

}
