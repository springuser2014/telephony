package telephony.core.service.converter;

import telephony.core.entity.jpa.*;
import telephony.core.service.dto.AddressDto;
import telephony.core.service.dto.ContactDto;
import telephony.core.service.dto.ContactSearchDto;
import telephony.core.service.dto.PhoneNumberDto;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;

public class ContactConverter {

    public ContactDto contactToContactDto(Contact contact) {

        ContactDto dto = new ContactDto();
        return update(dto,contact);
    }

    private ContactDto update(ContactDto dto, Contact contact) {
        dto.setId(contact.getId());
        dto.setDetails(contact.getDetails());
        dto.setLabel(contact.getLabel());

        dto.setAddress(addressToDto(contact.getAddress()));

        if (isNotEmpty(contact.getEmails())) {
            for(Email email : contact.getEmails()) {
                dto.addEmail(email.getContent());
            }
        }

        if (isNotEmpty(contact.getPhonenumbers())) {
            for (PhoneNumber phoneNumber : contact.getPhonenumbers()) {
                dto.addPhoneNumber(phoneNumberToDto(phoneNumber));
            }
        }

        if (isNotEmpty(contact.getFaxes())) {
            for (Fax fax : contact.getFaxes()) {
                dto.addFax(fax.getContent());
            }
        }

        return dto;
    }

    public static AddressDto addressToDto(Address address) {
        AddressDto dto = new AddressDto();

        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setZipCode(address.getZipCode());

        return dto;
    }

    public static PhoneNumberDto phoneNumberToDto(PhoneNumber phoneNumber) {

        PhoneNumberDto dto = new PhoneNumberDto();
        dto.setNumber(phoneNumber.getContent());
        dto.setPrefix(phoneNumber.getPrefix());

        return dto;
    }

    public ContactSearchDto contactToContactSearchDto(Contact contact) {

        ContactSearchDto dto = new ContactSearchDto();
        update(dto,contact);

        boolean is = contact.getDeliveries().isEmpty() && contact.getSales().isEmpty();

        dto.setEditable(is);
        dto.setDeletable(is);


        return dto;
    }
}
