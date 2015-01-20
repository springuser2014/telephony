package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.ComplaintDeleteRequest;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintDeleteResource;

import static telephony.core.assertion.CommonAssertions.*;

public class ComplaintDeleteResourceImpl
extends TelephonyServerResource 
implements ComplaintDeleteResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductComplaintService productComplaintService;

	@Inject
	SaleComplaintService saleComplaintService;

	@Override
	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintDeleteResponse delete(ComplaintDeleteRequest request) {

		logger.info("ComplaintDeleteResourceImpl.delete starts");

		ComplaintDeleteResponse resp = new ComplaintDeleteResponse();

		try {

			if (isNotNull(productComplaintService.findById(request.getComplaintId()))) {
				resp = productComplaintService.deleteComplaint(request);
			} else if (isNotNull(productComplaintService.findById(request.getComplaintId()))) {
				resp = saleComplaintService.deleteComplaint(request);
			} else {
				resp.setSuccess(false);
				resp.setMessage("problem");
				resp.addError(Error.create("complaintId", "complaintId is wrong"));
			}

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
