package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ContactDetailsRequest extends AuthRequest {

    Long contactId;

    public ContactDetailsRequest() {
        super();
    }

    public ContactDetailsRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }
}
