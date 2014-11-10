package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.response.ComplaintAddCommentResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ComplaintAddCommentResource;

public class ComplaintAddCommentResourceImpl
extends TelephonyServerResource
implements ComplaintAddCommentResource {

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplaintAddCommentResponse addComment(ComplaintCommentDto request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
