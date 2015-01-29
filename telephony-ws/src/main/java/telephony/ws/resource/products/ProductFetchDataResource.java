package telephony.ws.resource.products;

import org.restlet.resource.Post;
import telephony.core.service.dto.request.ProductFetchDataRequest;
import telephony.core.service.dto.response.ProductFetchDataResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface ProductFetchDataResource {

    String URL = "/products/fetchData";

    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ProductFetchDataResponse fetchData(ProductFetchDataRequest request);
}
