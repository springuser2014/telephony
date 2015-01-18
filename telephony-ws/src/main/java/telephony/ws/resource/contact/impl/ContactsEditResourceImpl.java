package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ContactService;
import telephony.core.service.dto.request.ContactEditRequest;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsEditResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ContactsEditResourceImpl 
extends TelephonyServerResource
implements ContactsEditResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ContactService contactService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactEditResponse edit(ContactEditRequest request) {

		logger.info("ContactsEditResourceImpl.fetch starts");

		ContactEditResponse resp = new ContactEditResponse();

		try {

			resp = contactService.edit(request);

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
