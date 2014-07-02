package telephony.ws.resource.sale.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;

/**
 * asd.
 */
public class SalesDeleteResourceImpl extends TelephonyServerResource implements
		SalesDeleteResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleService saleService;
	
	/**
     * {@inheritDoc}
     */
	@Override
    @Delete("json")
    public JsonRepresentation delete(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }

}
