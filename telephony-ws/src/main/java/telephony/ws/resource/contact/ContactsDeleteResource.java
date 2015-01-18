package telephony.ws.resource.contact;

import org.restlet.resource.Delete;
import telephony.core.service.dto.request.ContactDeleteRequest;
import telephony.core.service.dto.response.ContactDeleteResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ContactsDeleteResource {

	String URL = "/contacts/delete";

	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ContactDeleteResponse delete(ContactDeleteRequest entity);

}