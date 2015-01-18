package telephony.core.service.dto.response;

import telephony.core.service.dto.ContactDto;

public class ContactDetailsResponse extends BasicResponse {

    private ContactDto contact;

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public ContactDto getContact() {
        return contact;
    }
}
