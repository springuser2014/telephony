package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.TaxEditRequestDto;

public interface TaxEditResource {

	String URL = "/taxes/edit";
	
	JsonRepresentation edit(TaxEditRequestDto req);
}
