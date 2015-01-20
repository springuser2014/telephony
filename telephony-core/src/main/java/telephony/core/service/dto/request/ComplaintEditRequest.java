package telephony.core.service.dto.request;

import telephony.core.service.dto.ComplaintEditDto;
import telephony.core.service.dto.SessionDto;

public abstract class ComplaintEditRequest<T extends ComplaintEditDto> extends AuthRequest {

    T complaint;

    public ComplaintEditRequest() {
        super();
    }

    public ComplaintEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public T getComplaint() {
        return complaint;
    }

    public void setComplaint(T complaint) {
        this.complaint = complaint;
    }
}
