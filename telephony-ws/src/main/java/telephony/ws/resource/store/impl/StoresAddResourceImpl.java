package telephony.ws.resource.store.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SessionService;
import telephony.core.service.StoreService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresAddResource;

/**
 * asd.
 */
public class StoresAddResourceImpl extends TelephonyServerResource implements
		StoresAddResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private StoreService storeService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Post("json")
	public JsonRepresentation add(JsonRepresentation entity) {
		return new JsonRepresentation("asd");

	}

}
