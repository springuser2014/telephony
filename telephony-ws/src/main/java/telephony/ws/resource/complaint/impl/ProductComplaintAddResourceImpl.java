package telephony.ws.resource.complaint.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.dto.request.ReportProductComplaintRequest;
import telephony.core.service.dto.response.ReportProductComplaintResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.ProductComplaintAddResource;

public class ProductComplaintAddResourceImpl
extends TelephonyServerResource
implements ProductComplaintAddResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReportProductComplaintResponse add(ReportProductComplaintRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
