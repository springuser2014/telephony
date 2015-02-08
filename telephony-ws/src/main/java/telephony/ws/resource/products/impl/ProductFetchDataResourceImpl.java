package telephony.ws.resource.products.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.PermissionChecker;
import telephony.core.service.ProductService;
import telephony.core.service.SessionManager;
import telephony.core.service.dto.request.ProductFetchDataRequest;
import telephony.core.service.dto.response.ProductFetchDataResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductFetchDataResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ProductFetchDataResourceImpl
extends TelephonyServerResource
implements ProductFetchDataResource {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    ProductService productService;

    @Inject
    SessionManager sessionManager;

    @Inject
    PermissionChecker permissionChecker;

    @Override
    @Post("json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductFetchDataResponse fetchData(ProductFetchDataRequest request) {

        logger.info("ProductsFetchResourceImpl.fetch starts");

        ProductFetchDataResponse resp = new ProductFetchDataResponse();

        try {
            resp = productService.fetchData(request);
        } catch(SessionServiceException e) {
            logger.error("sessionExpired", e);

            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
            resp.setMessage("sessionExpired");
            resp.setSuccess(false);

            return resp;
        } catch (Exception e) {
            logger.error("unrecognizedProblem", e);

            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
            resp.setMessage("unrecognizedProblem");
            resp.setSuccess(false);

            return resp;
        }

        if (resp.hasErrors()) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return resp;
        } else {
            getResponse().setStatus(Status.SUCCESS_OK);
            return resp;
        }
    }
}
