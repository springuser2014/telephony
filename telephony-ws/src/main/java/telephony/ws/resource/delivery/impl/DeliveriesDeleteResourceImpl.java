package telephony.ws.resource.delivery.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.DeliveryService;
import telephony.core.service.dto.request.DeliveryDeleteRequest;
import telephony.core.service.dto.response.BasicResponse;
import telephony.core.service.dto.response.DeliveryDeleteResponse;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesDeleteResource;

public class DeliveriesDeleteResourceImpl
extends TelephonyServerResource
implements DeliveriesDeleteResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	DeliveryService deliveryService;
	
	@Override
	@Delete("json")	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveryDeleteResponse delete(DeliveryDeleteRequest request) {

		logger.info("DeliveriesDeleteResourceImpl.delete starts");
		
		DeliveryDeleteResponse resp = new DeliveryDeleteResponse();
		
		try {

			resp = deliveryService.delete(request);

		} catch (SessionServiceException e) {
			
			logger.error("sessionExpired", e);

			getResponse().setStatus(Status.SUCCESS_OK);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			return resp;
		} catch (DeliveryServiceException e) {
			
			logger.error("internal problem", e);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			resp.setMessage("internalServerError");
			resp.setSuccess(false);

			return resp;

		} catch(Exception e) {

			logger.error("unrecognized problem", e);

			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			resp.setMessage("sessionExpired");
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
