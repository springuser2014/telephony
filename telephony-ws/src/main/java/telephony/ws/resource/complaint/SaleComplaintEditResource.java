package telephony.ws.resource.complaint;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.response.SaleComplaintEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SaleComplaintEditResource {

    String URL = "/sale/complaint/edit";

    @Put("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    SaleComplaintEditResponse edit(SaleComplaintEditRequest request);

}
