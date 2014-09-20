package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintDetailsRequestDto;
import telephony.core.service.dto.ComplaintDetailsResponseDto;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintDetailsResource;

public class ComplaintDetailsResourceImpl
extends TelephonyServerResource
implements ComplaintDetailsResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintDetailsResponseDto details(ComplaintDetailsRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}
}
