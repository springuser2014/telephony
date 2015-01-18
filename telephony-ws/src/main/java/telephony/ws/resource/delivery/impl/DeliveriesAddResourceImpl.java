package telephony.ws.resource.delivery.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.DeliveryService;
import telephony.core.service.StoreService;
import telephony.core.service.dto.request.DeliveryAddRequest;
import telephony.core.service.dto.response.DeliveryAddResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesAddResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class DeliveriesAddResourceImpl
extends TelephonyServerResource
implements DeliveriesAddResource {

    final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Inject
    DeliveryService deliveryService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveryAddResponse add(DeliveryAddRequest addRequest) {
		
		logger.info("DeliveriesAddResourceImpl.add starts");

		DeliveryAddResponse resp = new DeliveryAddResponse();

		try {

			resp = deliveryService.add(addRequest);
		
		} catch (SessionServiceException e) {
			logger.error("sessionExpired", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			return resp;
		} catch (Exception e) {
			logger.error("internal problem", e);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			resp.setMessage("internalServerError");
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
