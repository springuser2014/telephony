package telephony.ws.resource.tax.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.TaxService;
import telephony.core.service.dto.request.TaxDeleteRequest;
import telephony.core.service.dto.response.TaxDeleteResponse;
import telephony.core.service.dto.response.TaxEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.tax.TaxDeleteResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class TaxDeleteResourceImpl 
extends TelephonyServerResource 
implements TaxDeleteResource {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	TaxService taxService;

	@Override
	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TaxDeleteResponse delete(TaxDeleteRequest deleteRequest) {

		logger.info("TaxDeleteResourceImpl.delete starts");

		TaxDeleteResponse resp = new TaxDeleteResponse ();

		try {
			resp = taxService.delete(deleteRequest);
		} catch (SessionServiceException e) {
			logger.error("authorization occurred during deleting tax", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			logger.error("internal error occurred during deleting tax", e);

			resp.setMessage("internalError");
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
