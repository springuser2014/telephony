package telephony.ws.resource.tax;

import org.restlet.ext.json.JsonRepresentation;

import telephony.core.service.dto.request.TaxEditRequest;

public interface TaxEditResource {

	String URL = "/taxes/edit";
	
	JsonRepresentation edit(TaxEditRequest req);
}
