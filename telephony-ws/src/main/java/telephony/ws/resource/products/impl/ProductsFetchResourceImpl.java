package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.ProductService;
import telephony.core.service.dto.request.ProductFetchRequest;
import telephony.core.service.dto.response.ProductFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsFetchResource;

import com.google.inject.Inject;

public class ProductsFetchResourceImpl
extends TelephonyServerResource 
implements ProductsFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	ProductService productsService;
	
	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductFetchResponse fetch(ProductFetchRequest request) {

		logger.info("ProductsFetchResourceImpl.fetch starts");

		ProductFetchResponse resp = new ProductFetchResponse();

		try {

			resp = productsService.fetch(request);

		} catch (SessionServiceException e) {
			logger.error("sessionExpired", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			return resp;
		} catch (Exception e) {
			logger.error("unrecognizedProblem", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			resp.setMessage("unrecognizedProblem");
			resp.setSuccess(false);

			return resp;
		}

		if (resp.hasErrors()) {
			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return resp;
		} else {
			getResponse().setStatus(Status.SUCCESS_OK);
			return resp;
		}
	}
}
