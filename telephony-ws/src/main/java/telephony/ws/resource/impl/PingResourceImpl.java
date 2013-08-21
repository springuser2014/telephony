package telephony.ws.resource.impl;

import org.restlet.data.ChallengeResponse;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO : remove later
public class PingResourceImpl extends ServerResource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @return asd.
     */
    @Get("txt")
    public String toText() {

        logger.info("======================================================");
        logger.info("PingResource starts");

        StringBuilder sb = new StringBuilder("Restlet server alive. Method: ");
        sb.append(getRequest().getMethod());

        ChallengeResponse challengeResponse = getRequest()
                .getChallengeResponse();
        if (challengeResponse != null) {
            sb.append("/ Auth. scheme: ");
            sb.append(challengeResponse.getScheme());
        }

        return sb.toString();
    }
}