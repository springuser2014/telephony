package telephony.ws.resource.taxes;

import telephony.core.service.dto.TaxesFetchRequest;
import telephony.core.service.dto.TaxesFetchResponse;


public interface TaxesFetchResource {

	String URL = "/taxes/fetch";
	
	TaxesFetchResponse fetch(TaxesFetchRequest req);

}
