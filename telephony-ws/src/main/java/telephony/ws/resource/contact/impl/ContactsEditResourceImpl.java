package telephony.ws.resource.contact.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ContactsEditRequestDto;
import telephony.core.service.dto.ContactsEditResponseDto;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.contact.ContactsEditResource;

public class ContactsEditResourceImpl 
extends TelephonyServerResource
implements ContactsEditResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ContactsEditResponseDto edit(ContactsEditRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

}
