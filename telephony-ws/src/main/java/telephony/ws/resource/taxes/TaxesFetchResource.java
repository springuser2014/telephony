package telephony.ws.resource.taxes;

import telephony.core.service.dto.request.TaxesFetchRequest;
import telephony.core.service.dto.response.TaxesFetchResponse;


public interface TaxesFetchResource {

	String URL = "/taxes/fetch";
	
	TaxesFetchResponse fetch(TaxesFetchRequest req);

}
