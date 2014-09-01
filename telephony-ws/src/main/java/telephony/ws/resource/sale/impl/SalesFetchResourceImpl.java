package telephony.ws.resource.sale.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesFetchResource;

import com.google.inject.Inject;


/**
 * asd.
 */
public class SalesFetchResourceImpl 
extends TelephonyServerResource 
implements SalesFetchResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleService saleService;
	
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public JsonRepresentation fetch(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }

}
