package telephony.core.service.dto.response;

import telephony.core.service.dto.ContactSearchDto;

import java.util.ArrayList;
import java.util.List;

public class ContactFetchResponse extends BasicResponse {

    List<ContactSearchDto> contacts;
    Long countTotal;

    public ContactFetchResponse() {
        contacts = new ArrayList<>();
    }

    public List<ContactSearchDto> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactSearchDto> contacts) {
        this.contacts = contacts;
    }

    public void addContact(ContactSearchDto contactDto) {

        if (!this.contacts.contains(contactDto)) {
            this.contacts.add(contactDto);
        }
    }

    public void removeContact(ContactSearchDto contactDto) {

        if (this.contacts.contains(contactDto)) {
            this.contacts.remove(contactDto);
        }
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
