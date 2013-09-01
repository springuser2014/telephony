package telephony.ws.resource.user.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class UserAddStoresResourceImpl extends ServerResource {

    private final Representation representation = new JsonRepresentation("");

	private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @return asd.
     */
    @Put
    public Representation set() {
        return representation;
    }
}
