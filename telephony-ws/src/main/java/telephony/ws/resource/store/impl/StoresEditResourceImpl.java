package telephony.ws.resource.store.impl;

import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import telephony.core.service.StoreService;
import telephony.core.service.dto.request.StoreEditRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.dto.response.StoreEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class StoresEditResourceImpl 
extends TelephonyServerResource
implements StoresEditResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	StoreService storeService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StoreEditResponse edit(StoreEditRequest editRequest) {

		logger.info("StoresEditResourceImpl.edit starts");

		StoreEditResponse resp = new StoreEditResponse();

		try {
			resp = storeService.edit(editRequest);
		} catch (SessionServiceException e) {
			logger.error("error occurred during store editing",e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;

		} catch (Exception e) {
			logger.error("error occurred during store editing", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			return resp;
		}

		getResponse().setStatus(Status.SUCCESS_OK);

		return resp;
	}
}
