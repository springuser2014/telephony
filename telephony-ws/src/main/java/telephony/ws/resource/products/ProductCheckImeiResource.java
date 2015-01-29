package telephony.ws.resource.products;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductCheckImeiRequest;
import telephony.core.service.dto.response.ProductCheckImeiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductCheckImeiResource {

    String URL = "/product/isImeiAvailable";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ProductCheckImeiResponse checkAvailability(ProductCheckImeiRequest request);
}
