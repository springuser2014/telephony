package telephony.ws.resource.sale.impl;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.SaleService;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesEditResource;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SalesEditResourceImpl extends TelephonyServerResource
	implements SalesEditResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SaleService saleService;
	
    /**
     * {@inheritDoc}
     */
	@Override
    @Put("json")
    public JsonRepresentation edit(JsonRepresentation entity) {
    	return new JsonRepresentation("asd");
	}
}
