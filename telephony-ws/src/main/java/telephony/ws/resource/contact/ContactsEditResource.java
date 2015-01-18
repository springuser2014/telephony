package telephony.ws.resource.contact;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.ContactEditRequest;
import telephony.core.service.dto.response.ContactEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ContactsEditResource {

	String URL = "/contacts/edit";

	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ContactEditResponse edit(ContactEditRequest request);
}
