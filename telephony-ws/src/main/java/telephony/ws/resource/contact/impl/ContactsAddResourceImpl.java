package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ContactService;
import telephony.core.service.dto.request.ContactAddRequest;
import telephony.core.service.dto.response.ContactAddResponse;
import telephony.core.service.exception.ContactServiceException;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsAddResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ContactsAddResourceImpl
extends TelephonyServerResource
implements ContactsAddResource {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ContactService contactService;

	@Override
    @Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactAddResponse add(ContactAddRequest addRequest) {
    	
		logger.info("ContactsAddResourceImpl.add starts");

		ContactAddResponse resp = new ContactAddResponse();

		try {

			resp = contactService.add(addRequest);
		} catch (SessionServiceException e) {

			logger.info("sessionExpired", e);

			return resp;
		} catch (ContactServiceException e) {
			logger.info("sessionExpired", e);



			return resp;
		} catch (Exception e) {
			logger.info("error occurred", e);

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
