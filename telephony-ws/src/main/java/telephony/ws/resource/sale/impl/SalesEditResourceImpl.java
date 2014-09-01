package telephony.ws.resource.sale.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesEditResource;

import com.google.inject.Inject;

/**
 * asd.
 */
public class SalesEditResourceImpl 
extends TelephonyServerResource
implements SalesEditResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleService saleService;
	
	@Override
    @Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation edit(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
	}
}
