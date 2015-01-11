package telephony.ws.resource.store.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.StoreService;
import telephony.core.service.dto.request.StoreAddRequest;
import telephony.core.service.dto.response.StoreAddResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresAddResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class StoresAddResourceImpl
extends TelephonyServerResource 
implements StoresAddResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	StoreService storeService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StoreAddResponse add(StoreAddRequest addRequest) {

		logger.info("StoresAddResourceImpl.add starts");

		StoreAddResponse resp = new StoreAddResponse ();

		try {
			resp = storeService.add(addRequest);
		} catch (SessionServiceException e) {
			logger.error("error occurred during store adding",e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

			return resp;

		} catch (Exception e) {
			logger.error("error occurred during store adding", e);

			resp.setMessage("sessionExpired");
			resp.setSuccess(false);
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

			return resp;
		}

		getResponse().setStatus(Status.SUCCESS_OK);

		return resp;
	}
}
