package telephony.ws.resource.taxes.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.TaxService;
import telephony.core.service.dto.request.TaxFetchRequest;
import telephony.core.service.dto.response.TaxFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.taxes.TaxesFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class TaxesFetchResourceImpl
extends TelephonyServerResource
implements TaxesFetchResource {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	TaxService taxService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TaxFetchResponse fetch(TaxFetchRequest fetchRequest) {

		logger.info("TaxesFetchResourceImpl.fetch starts");

		TaxFetchResponse resp = new TaxFetchResponse();

		try {
			resp = taxService.fetch(fetchRequest);
		} catch (SessionServiceException e) {
			logger.error("error occurred during fetching taxes", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			return resp;

		} catch (Exception e) {
			logger.error("internal error occurred during fetching taxes", e);

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
