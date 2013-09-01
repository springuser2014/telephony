package telephony.ws.resource.contact.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.ContactService;
import telephony.core.service.StoreService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsDeleteResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactsDeleteResourceImpl extends TelephonyServerResource
	implements ContactsDeleteResource {
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ContactService contactService;


	/**
	 * {@inheritDoc}
	 */
	@Delete("json")
	public final JsonRepresentation delete(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }
}
