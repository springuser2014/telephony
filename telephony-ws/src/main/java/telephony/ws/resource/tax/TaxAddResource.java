package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.TaxAddRequestDto;

public interface TaxAddResource {

	String URL = "/taxes/add";
	
	JsonRepresentation add(TaxAddRequestDto req);
}
