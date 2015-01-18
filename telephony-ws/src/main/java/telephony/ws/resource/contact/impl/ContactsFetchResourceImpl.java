package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.json.JSONException;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.entity.jpa.Contact;
import telephony.core.service.ContactService;
import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.request.ContactFetchRequest;
import telephony.core.service.dto.response.ContactFetchResponse;
import telephony.core.service.dto.response.ContactListResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsFetchResourceImpl
extends TelephonyServerResource
implements ContactsFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ContactService contactService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactFetchResponse fetch(ContactFetchRequest fetchRequest) {

		logger.info("ContactsFetchResourceImpl.fetch starts");

		ContactFetchResponse resp = new ContactFetchResponse();

		try {

			resp = contactService.fetch(fetchRequest);

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
