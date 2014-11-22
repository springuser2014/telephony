package telephony.ws.resource.contact.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.request.ContactsEditRequest;
import telephony.core.service.dto.response.ContactEditResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsEditResource;

public class ContactsEditResourceImpl 
extends TelephonyServerResource
implements ContactsEditResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactEditResponse edit(ContactsEditRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
