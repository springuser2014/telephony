package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintEditRequest;
import telephony.core.service.dto.ComplaintEditResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintEditResource;

public class ComplaintEditResourceImpl 
extends TelephonyServerResource
implements ComplaintEditResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintEditResponse edit(ComplaintEditRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
