package telephony.ws.resource.taxes;

import telephony.core.service.dto.TaxesFetchRequestDto;
import telephony.core.service.dto.TaxesFetchResponseDto;


public interface TaxesFetchResource {

	String URL = "/taxes/fetch";
	
	TaxesFetchResponseDto fetch(TaxesFetchRequestDto req);

}
