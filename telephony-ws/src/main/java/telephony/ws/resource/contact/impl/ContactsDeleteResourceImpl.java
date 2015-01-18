package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ContactService;
import telephony.core.service.dto.request.ContactDeleteRequest;
import telephony.core.service.dto.response.ContactDeleteResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsDeleteResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ContactsDeleteResourceImpl
extends TelephonyServerResource
implements ContactsDeleteResource {
		
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Inject
	ContactService contactService;

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactDeleteResponse delete(ContactDeleteRequest deleteRequest) {
    	
		logger.info("ContactsDeleteResource.delete starts");
		
		ContactDeleteResponse resp = new ContactDeleteResponse();

		try {

			resp = contactService.delete(deleteRequest);
			
		} catch (SessionServiceException e) {
			
			logger.error("sessionExpired", e);
			
			getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			
			resp.setSuccess(false);
			resp.setMessage("Error occurred");

			return resp;
		} catch (ContactServiceException e) {
			logger.error("session", e);

			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);

			resp.setSuccess(false);
			resp.setMessage("Error occurred");

			return resp;
		} catch (Exception e) {
			logger.error("unrecognizedProblem", e);

			getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);

			resp.setSuccess(false);
			resp.setMessage("unrecognizedProblem");

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
