package telephony.ws.resource.delivery.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.DeliveryService;
import telephony.core.service.dto.request.DeliveryEditRequest;
import telephony.core.service.dto.response.DeliveryEditResponse;
import telephony.core.service.exception.DeliveryServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.delivery.DeliveriesEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class DeliveriesEditResourceImpl
extends TelephonyServerResource 
implements DeliveriesEditResource {

	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	DeliveryService deliveryService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public DeliveryEditResponse edit(DeliveryEditRequest editRequest) {
		
		logger.info("DeliveriesEditResourceImpl.edit starts");

		DeliveryEditResponse resp = new DeliveryEditResponse();

		try {
			resp = deliveryService.edit(editRequest);
		} catch (SessionServiceException e) {
			
			logger.error("sessionExpired", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			resp.setMessage("sessionExpired");
			resp.setSuccess(false);

			return resp;

		} catch (DeliveryServiceException e) {
			
			logger.error("internalServerError", e);

			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			resp.setMessage("internalServerError");
			resp.setSuccess(false);

			return resp;
		
		} catch(Exception e) {

			logger.error("unrecognized problem", e);

			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			resp.setMessage("anotherProblem");
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
