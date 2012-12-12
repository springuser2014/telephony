package telephony.ws.resource;

import org.restlet.data.ChallengeResponse;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Resource that simply returns a plain text message.
 */
public class PingResource extends ServerResource {

    @Get("txt")
    public String toText() {
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