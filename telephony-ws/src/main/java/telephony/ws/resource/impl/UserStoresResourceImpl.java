package telephony.ws.resource.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserStoresResourceImpl extends ServerResource {

    private final Representation representation = new JsonRepresentation("");

    /**
     * asd.
     * @return asd.
     */
    @Put
    public Representation set() {
        return representation;
    }
}
