package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.SaleComplaintFetchRequest;
import telephony.core.service.dto.response.SaleComplaintFetchResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.SaleComplaintFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SaleComplaintFetchResourceImpl
extends TelephonyServerResource
implements SaleComplaintFetchResource {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    SaleComplaintService complaintService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest request) {
        return null;
    }
}
