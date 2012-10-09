package telephony.shared.gwtp;

import com.gwtplatform.dispatch.shared.Result;

public class SendTextToServerResult implements Result {

    private static final long serialVersionUID = 4621412923270714510L;

    private String response;
    
    public SendTextToServerResult(final String response) {
        this.response = response;
    }
    
    @SuppressWarnings("unused")
    private SendTextToServerResult() {
    }
    
    public String getResponse() {
        return response;
    }
}
