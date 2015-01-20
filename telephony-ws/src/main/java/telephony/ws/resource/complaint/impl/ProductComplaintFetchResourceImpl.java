package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.response.ProductComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ProductComplaintFetchResourceImpl
extends TelephonyServerResource 
implements ProductComplaintFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductComplaintService productComplaintService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductComplaintFetchResponse fetch(ProductComplaintFetchRequest request) {

		logger.info("ProductComplaintFetchResourceImpl.edit starts");

		ProductComplaintFetchResponse resp = new ProductComplaintFetchResponse();

		try {
			resp = productComplaintService.fetch(request);
		} catch (SessionServiceException e) {
			logger.info("sessionExpired", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			logger.info("error occurred", e);

			resp.setMessage("error occurred");
			resp.setSuccess(false);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

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
