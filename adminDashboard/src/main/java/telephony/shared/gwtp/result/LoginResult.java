package telephony.shared.gwtp.result;

import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.Result;
import telephony.shared.UserContext;

public class LoginResult implements Result {

    private boolean success;
    private String errorMessage;
    private String referrerUrl;
    private UserContext userContext;

    public LoginResult() {
    }

    public boolean isSuccess() {
        return success;

        ActionHandler
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public UserContext getUserContext() {
        return userContext;
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }
}
