package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Email;
import telephony.core.entity.jpa.PhoneNumber;

public class ContactDto {
	
	private Long id;
	private String label;
	private String details;
	private AddressDto address;
	private List<String> emails = new ArrayList<>();
	private List<PhoneNumberDto> phoneNumbers = new ArrayList<>();
	private List<String> faxes = new ArrayList<>();

	public ContactDto() {

	}

	/**
	 * asd.
	 * @param email a.
	 */
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
	/**
	 * asd.
	 * @param email asd.
	 */
	public void removeEmail(String email) {
		
		if (this.emails.contains(email)) {
			this.emails.remove(email);
		}
	}

	/**
	 * asd.
	 * @return a.
	 */
	public List<String> getEmails() {
		return emails;
	}

	/**
	 * asd.
	 * @param emails a.
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}


	/**
	 * asd.
	 * @param id asd . 
	 * @param label asd.
	 * @param details asd.
	 */
	public ContactDto(Long id, String label, String details) {
		super();
		this.id = id;
		this.label = label;
		this.details = details;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ContactDto other = (ContactDto) obj;
		if (details == null) {
			if (other.details != null) {
				return false;
			}
		} else if (!details.equals(other.details)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (label == null) {
			if (other.label != null) {
				return false;
			}
		} else if (!label.equals(other.label)) {
			return false;
		}
		return true;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * asd.
	 * @param id asd.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * asd.
	 * @param label asd.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * asd
	 * @return asd.
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * asd
	 * @param details asd.
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	public void setPhonenumbers(List<PhoneNumberDto> phonenumbers) {
		this.phoneNumbers = phonenumbers;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public List<String> getFaxes() {
		return faxes;
	}

	public void addFax(String fax) {

		if (!this.faxes.contains(fax)) {
			this.faxes.add(fax);
		}
	}

	public void removeFax(String fax) {

		if (this.faxes.contains(fax)) {
			this.faxes.remove(fax);
		}
	}

	/**
	 * asd.
	 * @param faxes a.
	 */
	public void setFaxes(List<String> faxes) {
		this.faxes = faxes;
	}

	/**
	 * asd.
	 * @param phoneNumberDto a.
	 */
	public void addPhoneNumber(PhoneNumberDto phoneNumberDto) {

		if (!this.phoneNumbers.contains(phoneNumberDto)) {
			this.phoneNumbers.add(phoneNumberDto);
		}
	}

	/**
	 * ads.
	 * @param phoneNumberDto a.
	 */
	public void removePhoneNumber(PhoneNumberDto phoneNumberDto) {

		if (this.phoneNumbers.add(phoneNumberDto)) {
			this.phoneNumbers.remove(phoneNumberDto);
		}
	}

	public List<PhoneNumberDto> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumberDto> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}


	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
}
