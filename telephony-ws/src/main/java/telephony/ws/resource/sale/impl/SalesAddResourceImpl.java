package telephony.ws.resource.sale.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleService;
import telephony.core.service.dto.request.SaleAddRequest;
import telephony.core.service.dto.response.SaleAddResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesAddResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SalesAddResourceImpl
extends TelephonyServerResource 
implements SalesAddResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	SaleService saleService;

	@Override
    @Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public SaleAddResponse add(SaleAddRequest addRequest) {

		logger.info("SalesAddResourceImpl.add starts");

		SaleAddResponse resp = new SaleAddResponse();

		try {

			resp = saleService.add(addRequest);

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
