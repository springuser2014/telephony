package telephony.ws.resource.sale.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleService;
import telephony.core.service.dto.request.SaleEditRequest;
import telephony.core.service.dto.response.SaleEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SalesEditResourceImpl
extends TelephonyServerResource
implements SalesEditResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	SaleService saleService;
	
	@Override
    @Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public SaleEditResponse edit(SaleEditRequest editRequest) {

		logger.info("SalesEditResourceImpl.edit starts");

		SaleEditResponse resp = new SaleEditResponse();

		try {

			resp = saleService.edit(editRequest);

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
