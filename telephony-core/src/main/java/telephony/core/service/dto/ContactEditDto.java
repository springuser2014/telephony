package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactEditDto extends ContactDto {
	
	private List<String> emailsToRemove = new ArrayList<String>();
	
	private List<PhoneNumberDto> phoneNumbersToRemove = new ArrayList<PhoneNumberDto>();

	private List<String> faxesToRemove = new ArrayList<String>();

	public List<String> getEmailsToRemove() {
		return emailsToRemove;
	}

	public void setEmailsToRemove(List<String> emailsToRemove) {
		this.emailsToRemove = emailsToRemove;
	}

	public List<PhoneNumberDto> getPhoneNumbersToRemove() {
		return phoneNumbersToRemove;
	}

	public void setPhoneNumbersToRemove(List<PhoneNumberDto> phoneNumbersToRemove) {
		this.phoneNumbersToRemove = phoneNumbersToRemove;
	}

	public List<String> getFaxesToRemove() {
		return faxesToRemove;
	}

	public void setFaxesToRemove(List<String> faxesToRemove) {
		this.faxesToRemove = faxesToRemove;
	}

	public void addEmailToRemove(String email) {

		if (!this.emailsToRemove.contains(email)) {
			this.emailsToRemove.add(email);
		}
	}

	public void removeEmailToRemove(String email) {

		if (this.emailsToRemove.contains(email)) {
			this.emailsToRemove.remove(email);
		}
	}
	
	public void addPhoneNumberToRemove(PhoneNumberDto phonenumber) {
		
		if (!this.phoneNumbersToRemove.contains(phonenumber)) {
			this.phoneNumbersToRemove.add(phonenumber);
		}
	}
	
	public void removePhoneNumberToRemove(PhoneNumberDto phonenumber) {
		
		if (this.phoneNumbersToRemove.contains(phonenumber)) {
			this.phoneNumbersToRemove.remove(phonenumber);
		}
	}

	public void addFaxToRemove(String fax) {

		if (!this.faxesToRemove.contains(fax)) {
			this.faxesToRemove.add(fax);
		}
	}

	public void removeFaxToRemove(String fax) {

		if (this.faxesToRemove.contains(fax)) {
			this.faxesToRemove.remove(fax);
		}
	}

}
