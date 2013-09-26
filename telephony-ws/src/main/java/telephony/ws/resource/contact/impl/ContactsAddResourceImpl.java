package telephony.ws.resource.contact.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.ContactService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsAddResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ContactsAddResourceImpl extends TelephonyServerResource implements
		ContactsAddResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ContactService contactService;

	/**
	 * {@inheritDoc}
	 */
	@Override
    @Post("json")
	public final JsonRepresentation add(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }

}