package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintEditRequestDto;
import telephony.core.service.dto.ComplaintEditResponseDto;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintEditResource;

public class ComplaintEditResourceImpl 
extends TelephonyServerResource
implements ComplaintEditResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintEditResponseDto edit(ComplaintEditRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
