package telephony.ws.resource.contact.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ContactService;
import telephony.core.service.dto.request.ContactDetailsRequest;
import telephony.core.service.dto.response.ContactDetailsResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsDetailsResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ContactsDetailsResourceImpl
extends TelephonyServerResource
implements ContactsDetailsResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    ContactService contactService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ContactDetailsResponse details(ContactDetailsRequest fetchRequest) {

        logger.info("ContactsFetchResourceImpl.fetch starts");

        ContactDetailsResponse resp = new ContactDetailsResponse();

        try {

            resp = contactService.fetchDetails(fetchRequest);

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
