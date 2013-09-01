package telephony.ws.resource.delivery.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesFetchResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class DeliveriesFetchResourceImpl extends TelephonyServerResource
	implements DeliveriesFetchResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Post("json")
	public JsonRepresentation fetch(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
