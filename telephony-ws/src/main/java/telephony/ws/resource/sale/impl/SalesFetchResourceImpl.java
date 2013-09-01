package telephony.ws.resource.sale.impl;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesFetchResource;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SalesFetchResourceImpl extends TelephonyServerResource 
	implements SalesFetchResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleService saleService;
	
    /**
     * {@inheritDoc}
     */
    @Post("json")
    public JsonRepresentation fetch(JsonRepresentation entity) {
        return new JsonRepresentation("asd");
    }

}
