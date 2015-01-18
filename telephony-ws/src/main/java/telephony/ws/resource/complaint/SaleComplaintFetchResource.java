package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.SaleComplaintFetchRequest;
import telephony.core.service.dto.response.SaleComplaintFetchResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface SaleComplaintFetchResource {

    String URL = "/product/complaint/fetch";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    SaleComplaintFetchResponse fetch(SaleComplaintFetchRequest request);
}
