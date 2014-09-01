package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.DeliveryDetailsRequest;
import telephony.core.service.dto.DeliveryDetailsResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesDetailsResource;

public class DeliveriesDetailsResourceImpl 
extends TelephonyServerResource 
implements DeliveriesDetailsResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveryDetailsResponse details(DeliveryDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
