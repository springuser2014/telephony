package telephony.ws.resource.tax.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.TaxService;
import telephony.core.service.dto.request.TaxAddRequest;
import telephony.core.service.dto.response.TaxAddResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.tax.TaxAddResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class TaxAddResourceImpl
extends TelephonyServerResource 
implements TaxAddResource {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	TaxService taxService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TaxAddResponse add(TaxAddRequest addRequest) {

		logger.info("TaxAddResourceImpl.add starts");

		TaxAddResponse resp = new TaxAddResponse();

		try {
			resp = taxService.add(addRequest);
		} catch (SessionServiceException e) {
			logger.error("error occurred during adding tax", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;

		} catch (Exception e) {
			logger.error("internal error occurred during adding tax", e);

			resp.setMessage("internalError");
			resp.setSuccess(false);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			return resp;
		}

		resp.setMessage("operation performed successfully");
		resp.setSuccess(true);

		getResponse().setStatus(Status.SUCCESS_OK);

		return resp;
	}
}
