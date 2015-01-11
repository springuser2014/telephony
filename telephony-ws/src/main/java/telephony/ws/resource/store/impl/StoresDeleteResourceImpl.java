package telephony.ws.resource.store.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.StoreService;
import telephony.core.service.dto.request.StoreDeleteRequest;
import telephony.core.service.dto.response.StoreDeleteResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresDeleteResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class StoresDeleteResourceImpl
extends TelephonyServerResource 
implements StoresDeleteResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	StoreService storeService;

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StoreDeleteResponse delete(StoreDeleteRequest deleteRequest) {

		logger.info("StoresEditResourceImpl.edit starts");

		StoreDeleteResponse resp = new StoreDeleteResponse();

		try {
			resp = storeService.delete(deleteRequest);
		} catch (SessionServiceException e) {
			logger.error("error occurred during store editing", e);

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
