package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.GsonBuilder;

import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.dto.ProductDetailsRequest;
import telephony.core.service.dto.ProductDetailsResponse;
import telephony.core.service.dto.ProductFetchRequest;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsDetailsResource;

public class ProductsDetailsResourceImpl
extends TelephonyServerResource 
implements ProductsDetailsResource {
	
	public static void main(String args[]) {
		com.google.gson.Gson gson = new GsonBuilder().serializeNulls().create();
		
		ProductFetchRequest req = new ProductFetchRequest();
		req.setSessionId("asd");
		req.setUsername("asd");
		
		ProductFilterCriteria c = ProductFilterCriteria.create().page(1);
		req.setFiltersCriteria(c);
		
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
