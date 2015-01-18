package telephony.ws.resource.products.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductService;
import telephony.core.service.dto.request.DeliveryDetailsRequest;
import telephony.core.service.dto.request.ProductDetailsRequest;
import telephony.core.service.dto.response.ProductDetailsResponse;
import telephony.core.service.dto.response.ProductFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductsDetailsResource;

import com.google.gson.GsonBuilder;

public class ProductsDetailsResourceImpl
extends TelephonyServerResource 
implements ProductsDetailsResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductService productService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductDetailsResponse details(ProductDetailsRequest request) {

		logger.info("ProductsDetailsResourceImpl.fetch starts");

		ProductDetailsResponse resp = new ProductDetailsResponse();

		try {

			resp = productService.fetchDetails(request);

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
