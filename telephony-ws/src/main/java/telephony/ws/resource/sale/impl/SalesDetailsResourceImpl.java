package telephony.ws.resource.sale.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.SalesDetailsRequest;
import telephony.core.service.dto.SalesDetailsResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesDetailsResource;

public class SalesDetailsResourceImpl 
extends TelephonyServerResource 
implements SalesDetailsResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SalesDetailsResponse details(SalesDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
