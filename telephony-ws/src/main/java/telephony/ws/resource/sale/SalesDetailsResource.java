package telephony.ws.resource.sale;

import telephony.core.service.dto.request.SaleDetailsRequest;
import telephony.core.service.dto.response.SaleDetailsResponse;

public interface SalesDetailsResource {

	String URL = "/sales/details";
	
	SaleDetailsResponse details(SaleDetailsRequest request);
}
