package telephony.ws.resource.delivery.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesAddResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesAddResourceImpl extends TelephonyServerResource
		implements DeliveriesAddResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Post("json")
	public JsonRepresentation add(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
