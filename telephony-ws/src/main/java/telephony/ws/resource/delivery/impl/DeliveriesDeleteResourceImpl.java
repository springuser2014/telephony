package telephony.ws.resource.delivery.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesDeleteResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesDeleteResourceImpl extends TelephonyServerResource
	implements DeliveriesDeleteResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Delete("json")	
	public JsonRepresentation delete(JsonRepresentation entity) {
		
		return new JsonRepresentation("asd");
	}

}
