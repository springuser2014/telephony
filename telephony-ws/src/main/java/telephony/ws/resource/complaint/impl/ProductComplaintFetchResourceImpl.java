package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.response.ProductComplaintFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ProductComplaintFetchResourceImpl
extends TelephonyServerResource 
implements ProductComplaintFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	ProductComplaintService complaintService;

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductComplaintFetchResponse fetch(ProductComplaintFetchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
