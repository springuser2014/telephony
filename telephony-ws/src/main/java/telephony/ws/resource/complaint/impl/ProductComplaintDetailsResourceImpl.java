package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.dto.request.ComplaintDetailsRequest;
import telephony.core.service.dto.request.ProductComplaintDetailsFetchRequest;
import telephony.core.service.dto.response.ComplaintDetailsResponse;
import telephony.core.service.dto.response.ProductComplaintDetailsFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintDetailsResource;

public class ProductComplaintDetailsResourceImpl
extends TelephonyServerResource
implements ProductComplaintDetailsResource {

	final Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ProductComplaintDetailsFetchResponse details(ProductComplaintDetailsFetchRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
