package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.TaxDeleteRequestDto;

public interface TaxDeleteResource {

	String URL = "/taxes/delete";
	
	JsonRepresentation delete(TaxDeleteRequestDto req);
}
