package war.shared;


import java.io.Serializable;

public class RPCServiceStatus implements Serializable {
    
    private String operationStatusInfo;

    public enum Status { SUCCESS, FAILED };

    private Status status;
    
    public RPCServiceStatus() {}

    public RPCServiceStatus(String operationStatusInfo, Status status) {
        this.operationStatusInfo = operationStatusInfo;
        this.status = status;
    }

    public String getOperationStatusInfo() {
        return operationStatusInfo;
    }

    public void setOperationStatusInfo(String operationStatusInfo) {
        this.operationStatusInfo = operationStatusInfo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
