package telephony.ws.resource.store.impl;

import com.google.inject.Inject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.service.StoreService;
import telephony.core.service.dto.request.StoreFetchRequest;
import telephony.core.service.dto.response.StoreFetchResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.exception.StoreServiceException;
import telephony.ws.resource.TelephonyServerResource;
import telephony.ws.resource.store.StoresFetchResource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class StoresFetchResourceImpl
extends TelephonyServerResource 
implements StoresFetchResource {

	final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    StoreService storeService;

    @Override
    @Post("json")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public StoreFetchResponse fetch(StoreFetchRequest fetchRequest) {

        logger.info("StoresFetchResourceImpl.fetch starts");

        StoreFetchResponse resp = new StoreFetchResponse();

        try {
            resp = storeService.fetch(fetchRequest);
        } catch (SessionServiceException e) {
            logger.error("error occurred during stores fetching",e);

            resp.setMessage("sessionExpired");
            resp.setSuccess(false);
            getResponse().setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);

            return resp;

        } catch (Exception e) {
            logger.error("error occurred during stores fetching", e);

            resp.setMessage("sessionExpired");
            resp.setSuccess(false);
            getResponse().setStatus(Status.SERVER_ERROR_INTERNAL);

            return resp;
        }

        getResponse().setStatus(Status.SUCCESS_OK);

        return resp;
    }
}
