package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintAddRequest;
import telephony.core.service.dto.ComplaintAddResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintAddResource;

public class ComplaintAddResourceImpl
extends TelephonyServerResource
implements ComplaintAddResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintAddResponse add(ComplaintAddRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}