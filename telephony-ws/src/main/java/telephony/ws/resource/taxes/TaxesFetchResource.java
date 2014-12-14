package telephony.ws.resource.taxes;

import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxFetchResponse;


public interface TaxesFetchResource {

	String URL = "/taxes/fetch";
	
	TaxFetchResponse fetch(TaxFetchRequest req);

}
