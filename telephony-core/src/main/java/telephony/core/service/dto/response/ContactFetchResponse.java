package telephony.core.service.dto.response;

import telephony.core.service.dto.ContactDto;

import java.util.ArrayList;
import java.util.List;

public class ContactFetchResponse extends BasicResponse {

    List<ContactDto> contacts;

    public ContactFetchResponse() {
        contacts = new ArrayList<ContactDto>();
    }

    public List<ContactDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDto> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactDto contactDto) {

        if (!this.contacts.contains(contactDto)) {
            this.contacts.add(contactDto);
        }
    }

    public void removeContact(ContactDto contactDto) {

        if (this.contacts.contains(contactDto)) {
            this.contacts.remove(contactDto);
        }
    }
}
