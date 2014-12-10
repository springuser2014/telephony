package telephony.core.service.dto.response;

public class Error {

    String fieldId;
    String errorMsg;

    public Error() {

    }

    public Error(String fieldId, String errorMsg) {
        this.fieldId = fieldId;
        this.errorMsg = errorMsg;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
