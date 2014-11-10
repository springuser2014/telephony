package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.ProductService;
import telephony.core.service.dto.request.ProductFetchRequestDto;
import telephony.core.service.dto.response.BasicResponseDto;
import telephony.core.service.dto.response.ProductFetchResponseDto;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsFetchResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;

public class ProductsFetchResourceImpl
extends TelephonyServerResource 
implements ProductsFetchResource {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	private ProductService productsService;
	
	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JsonRepresentation fetch(ProductFetchRequestDto request) {
		
		Gson gson = new GsonBuilder().serializeNulls().create();
		
		ProductFetchResponseDto resp = new ProductFetchResponseDto();
		
		try {
			resp = productsService.find(request);
		} catch (SessionServiceException e) {
			
			logger.error("session problem", e);
			return new JsonRepresentation(gson.toJson(new BasicResponseDto(false, "session error")));
		}
		
		return new JsonRepresentation(gson.toJson(resp)); 
	}
}
