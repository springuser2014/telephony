package telephony.ws.resource.contact;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ContactFetchRequest;
import telephony.core.service.dto.response.ContactFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ContactsFetchResource {

	String URL = "/contacts/fetch";
	
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ContactFetchResponse fetch(ContactFetchRequest fetchRequest);

}