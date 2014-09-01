package telephony.ws.resource.sale.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesAddResource;

/**
 * asd.
 */
public class SalesAddResourceImpl extends TelephonyServerResource 
	implements SalesAddResource {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private SaleService saleService;

	@Override
    @Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation post(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
    }

}
