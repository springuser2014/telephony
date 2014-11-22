package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.request.DeliveryDetailsRequest;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.response.ProductDetailsResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsDetailsResource;

import com.google.gson.GsonBuilder;

public class ProductsDetailsResourceImpl
extends TelephonyServerResource 
implements ProductsDetailsResource {
	
	public static void main(String args[]) {
		com.google.gson.Gson gson = new GsonBuilder().serializeNulls().create();
		
		DeliveryDetailsRequest req = new DeliveryDetailsRequest();
		req.setSessionId("asd");
		req.setUsername("foo");
		req.setDeliveryId(1l);
		
		System.out.println(gson.toJson(req));	
	}	

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductDetailsResponse details(ProductDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
