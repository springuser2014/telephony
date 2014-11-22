package telephony.ws.resource.sale;

import telephony.core.service.dto.request.SalesDetailsRequest;
import telephony.core.service.dto.response.SalesDetailsResponse;

public interface SalesDetailsResource {

	String URL = "/sales/details";
	
	SalesDetailsResponse details(SalesDetailsRequest request);
}
