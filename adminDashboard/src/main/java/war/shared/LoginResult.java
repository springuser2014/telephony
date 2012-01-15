package war.shared;

import java.io.Serializable;

public class LoginResult implements Serializable {
  private static final long serialVersionUID = -5076270075962361818L;

  private boolean success;
  private String errorMessage;
  private String referrerUrl;

  public LoginResult() {
  }

  public boolean isSuccess() {
    return success;
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
}
