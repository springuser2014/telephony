package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.resource.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.dto.request.ComplaintDeleteRequest;
import telephony.core.service.dto.response.ComplaintDeleteResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintDeleteResource;

public class ComplaintDeleteResourceImpl
extends TelephonyServerResource 
implements ComplaintDeleteResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Delete("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintDeleteResponse delete(ComplaintDeleteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
