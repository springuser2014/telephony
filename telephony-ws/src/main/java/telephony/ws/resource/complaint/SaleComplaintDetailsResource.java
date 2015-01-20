package telephony.ws.resource.complaint;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.SaleComplaintDetailsFetchRequest;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.response.SaleComplaintDetailsFetchResponse;
import telephony.core.service.dto.response.SaleComplaintEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SaleComplaintDetailsResource {

    String URL = "/sale/complaint/details";

    @Put("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    SaleComplaintDetailsFetchResponse details(SaleComplaintDetailsFetchRequest request);
}
