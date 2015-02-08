package telephony.ws.resource.products.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.PermissionChecker;
import telephony.core.service.ProductService;
import telephony.core.service.SessionManager;
import telephony.core.service.dto.request.ProductCheckImeiRequest;
import telephony.core.service.dto.response.ProductCheckImeiResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.products.ProductCheckImeiResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class ProductCheckImeiResourceImpl
extends TelephonyServerResource
implements ProductCheckImeiResource {

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
    public ProductCheckImeiResponse checkAvailability(ProductCheckImeiRequest request) {

        logger.info("ProductCheckImeiResourceImpl.checkAvailability strats");

        ProductCheckImeiResponse resp = new ProductCheckImeiResponse();
        try {
            resp = productService.checkImei(request);
        } catch (SessionServiceException e) {
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
