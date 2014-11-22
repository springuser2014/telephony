package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.request.TaxDeleteRequest;

public interface TaxDeleteResource {

	String URL = "/taxes/delete";
	
	JsonRepresentation delete(TaxDeleteRequest req);
}
