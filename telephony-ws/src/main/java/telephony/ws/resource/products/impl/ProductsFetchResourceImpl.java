package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ProductFetchRequest;
import telephony.core.service.dto.ProductFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsFetchResource;

public class ProductsFetchResourceImpl
extends TelephonyServerResource 
implements ProductsFetchResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductFetchResponse fetch(ProductFetchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
