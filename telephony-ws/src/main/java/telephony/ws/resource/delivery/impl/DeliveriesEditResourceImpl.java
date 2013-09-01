package telephony.ws.resource.delivery.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesEditResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesEditResourceImpl extends TelephonyServerResource 
	implements DeliveriesEditResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Put("json")
	public JsonRepresentation edit(JsonRepresentation entity) {
		
		return new JsonRepresentation("asd");
	}
}
