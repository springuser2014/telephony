package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.Inject;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintEditResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintEditResource;

public class ProductComplaintEditResourceImpl
extends TelephonyServerResource
implements ProductComplaintEditResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductComplaintService complaintService;

	@Override
	@Put("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductComplaintEditResponse edit(ProductComplaintEditRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
