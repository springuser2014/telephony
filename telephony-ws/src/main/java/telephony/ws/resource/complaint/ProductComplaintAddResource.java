package telephony.ws.resource.complaint;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ReportProductComplaintRequest;
import telephony.core.service.dto.response.ReportProductComplaintResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductComplaintAddResource {
	
	String URL = "/product/complaint/add";

	@Post("json")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	ReportProductComplaintResponse add(ReportProductComplaintRequest request);

}
