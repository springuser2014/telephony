package telephony.ws.resource.sale.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.resource.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.service.SaleService;
import telephony.core.service.dto.request.SalesFetchRequest;
import telephony.core.service.dto.response.SalesFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.sale.SalesFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class SalesFetchResourceImpl
extends TelephonyServerResource 
implements SalesFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	SaleService saleService;
	
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public SalesFetchResponse fetch(SalesFetchRequest fetchRequest) {

        logger.info("SalesFetchResourceImpl.edit starts");

        SalesFetchResponse resp = new SalesFetchResponse();

        try {

            resp = saleService.findSales(fetchRequest);

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
