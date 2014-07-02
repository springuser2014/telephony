package telephony.ws.resource.store.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.StoreService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresEditResource;

/**
 * asd.
 */
public class StoresEditResourceImpl extends TelephonyServerResource
		implements StoresEditResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private StoreService storeService;

	@Override
	@Put("json")
	public JsonRepresentation edit(JsonRepresentation entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
