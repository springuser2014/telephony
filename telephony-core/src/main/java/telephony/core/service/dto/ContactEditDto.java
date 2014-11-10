package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ContactEditDto extends ContactDto {
	
	private List<String> emailsToRemove = new ArrayList<String>();
	
	private List<String> phonenumbersToRemove = new ArrayList<String>();

	public List<String> getEmailsToRemove() {
		return emailsToRemove;
	}

	public void setEmailsToRemove(List<String> emailsToRemove) {
		this.emailsToRemove = emailsToRemove;
	}

	public List<String> getPhonenumbersToRemove() {
		return phonenumbersToRemove;
	}

	public void setPhonenumbersToRemove(List<String> phonenumbersToRemove) {
		this.phonenumbersToRemove = phonenumbersToRemove;
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
	
	public void addPhonenumberToRemove(String phonenumber) {
		
		if (!this.phonenumbersToRemove.contains(phonenumber)) {
			this.phonenumbersToRemove.add(phonenumber);
		}
	}
	
	public void removePhonenumberToRemove(String phonenumber) {
		
		if (this.phonenumbersToRemove.contains(phonenumber)) {
			this.phonenumbersToRemove.remove(phonenumber);
		}
	}
}
