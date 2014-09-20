package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintDeleteRequestDto;
import telephony.core.service.dto.ComplaintDeleteResponseDto;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintDeleteResource;

public class ComplaintDeleteResourceImpl
extends TelephonyServerResource 
implements ComplaintDeleteResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintDeleteResponseDto delete(ComplaintDeleteRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
