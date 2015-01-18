package telephony.ws.resource.complaint.impl;

import com.google.inject.Inject;
import org.restlet.resource.Put;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.response.SaleComplaintEditResponse;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.complaint.SaleComplaintDetailsResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SaleComplaintDetailsResourceImpl
extends TelephonyServerResource
implements SaleComplaintDetailsResource
{
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    SaleComplaintService complaintService;

    @Override
    @Put("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SaleComplaintEditResponse edit(SaleComplaintEditRequest request) {
        return null;
    }
}
