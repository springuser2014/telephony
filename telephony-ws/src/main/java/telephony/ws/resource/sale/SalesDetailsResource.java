package telephony.ws.resource.sale;

import telephony.core.service.dto.SalesDetailsRequest;
import telephony.core.service.dto.SalesDetailsResponse;

public interface SalesDetailsResource {

	String URL = "/sales/details";
	
	SalesDetailsResponse details(SalesDetailsRequest request);
}
