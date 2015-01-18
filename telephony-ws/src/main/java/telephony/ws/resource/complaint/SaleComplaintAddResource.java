package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ReportSaleComplaintRequest;
import telephony.core.service.dto.response.ReportSaleComplaintResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SaleComplaintAddResource {

    String URL = "/sale/complaint/add";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ReportSaleComplaintResponse add(ReportSaleComplaintRequest request);
}
