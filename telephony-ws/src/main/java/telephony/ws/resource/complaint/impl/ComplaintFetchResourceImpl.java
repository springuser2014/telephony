package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.request.ComplaintFetchRequest;
import telephony.core.service.dto.response.ComplaintFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintFetchResource;

public class ComplaintFetchResourceImpl 
extends TelephonyServerResource 
implements ComplaintFetchResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintFetchResponse fetch(ComplaintFetchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
