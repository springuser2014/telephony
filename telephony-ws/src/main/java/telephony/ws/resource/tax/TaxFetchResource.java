package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.TaxFetchRequestDto;

public interface TaxFetchResource {

	String URL = "/taxes/fetch";
	
	JsonRepresentation fetch(TaxFetchRequestDto req);
}
