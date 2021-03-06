package telephony.ws.resource.contact;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ContactAddRequest;
import telephony.core.service.dto.response.ContactAddResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ContactsAddResource {
	
	String URL = "/contacts/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ContactAddResponse add(ContactAddRequest addRequest);

}