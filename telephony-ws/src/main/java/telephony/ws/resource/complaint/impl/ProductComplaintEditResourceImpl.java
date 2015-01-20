package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintDetailsFetchResponse;
import telephony.core.service.dto.response.ProductComplaintEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintEditResource;

public class ProductComplaintEditResourceImpl
extends TelephonyServerResource
implements ProductComplaintEditResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductComplaintService productComplaintService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductComplaintEditResponse edit(ProductComplaintEditRequest request) {

		logger.info("ProductComplaintEditResourceImpl.edit starts");

		ProductComplaintEditResponse resp = new ProductComplaintEditResponse();

		try {
			resp = productComplaintService.editComplaint(request);
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
