package telephony.ws.resource.contact;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ContactDetailsRequest;
import telephony.core.service.dto.response.ContactDetailsResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ContactsDetailsResource {

	String URL = "/contacts/details";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ContactDetailsResponse details(ContactDetailsRequest req);
}
