package telephony.ws.resource.contact.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.ContactService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsFetchResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
public class ContactsFetchResourceImpl extends TelephonyServerResource
	implements ContactsFetchResource {
	

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactService contactService;


	/**
	 * {@inheritDoc}
	 */
	@Post("json")
	public final JsonRepresentation fetch(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }
}
