package telephony.ws.resource.complaint;

import org.restlet.resource.Put;
import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintEditResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductComplaintEditResource {

	String URL = "/product/complaint/edit";

	@Put("json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	ProductComplaintEditResponse edit(ProductComplaintEditRequest request);
}
