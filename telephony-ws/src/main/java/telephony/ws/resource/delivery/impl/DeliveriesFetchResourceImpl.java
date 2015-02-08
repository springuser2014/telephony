package telephony.ws.resource.delivery.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.DeliveryService;
import telephony.core.service.dto.request.DeliveriesFetchRequest;
import telephony.core.service.dto.response.DeliveriesFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class DeliveriesFetchResourceImpl
extends TelephonyServerResource
implements DeliveriesFetchResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	DeliveryService deliveryService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveriesFetchResponse fetch(DeliveriesFetchRequest request) {

		logger.info("DeliveriesFetchResourceImpl.fetch starts");

		DeliveriesFetchResponse resp = new DeliveriesFetchResponse();
		
		try {
			resp = deliveryService.findDeliveries(request);
			
		} catch (SessionServiceException e) {
			
			logger.error("sessionExpired", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;
		} catch (Exception e) {
			
			logger.error("internalServerError", e);

			resp.setMessage("internalServerError");
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
