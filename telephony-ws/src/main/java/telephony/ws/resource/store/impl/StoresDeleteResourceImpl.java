package telephony.ws.resource.store.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.StoreService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresDeleteResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class StoresDeleteResourceImpl extends TelephonyServerResource implements
		StoresDeleteResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private StoreService storeService;


	/**
	 * {@inheritDoc}
	 */
	@Override
	@Delete("json")
	public JsonRepresentation delete(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
