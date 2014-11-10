package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.request.TaxFetchRequest;

public interface TaxFetchResource {

	String URL = "/taxes/fetch";
	
	JsonRepresentation fetch(TaxFetchRequest req);
}
