package telephony.ws.resource.taxes;

import telephony.core.service.dto.request.TaxesFetchRequestDto;
import telephony.core.service.dto.response.TaxesFetchResponseDto;


public interface TaxesFetchResource {

	String URL = "/taxes/fetch";
	
	TaxesFetchResponseDto fetch(TaxesFetchRequestDto req);

}
