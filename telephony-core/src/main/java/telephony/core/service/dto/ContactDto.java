package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Email;
import telephony.core.entity.jpa.PhoneNumber;

/**
 * asd.
 */
public class ContactDto {
	
	private Long id;
	
	private String label;
	
	private String details;
	
	private List<String> emails = new ArrayList<String>();
	
	private List<String> phonenumbers = new ArrayList<String>();

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
	 * @param phone a.
	 */
	public void addPhoneNumber(String phone) {
		this.phonenumbers.add(phone);
	}
	
	/**
	 * asd.
	 * @param phone a.
	 */
	public void removePhoneNumber(String phone) {
		
		if (this.phonenumbers.contains(phone)) {
			this.phonenumbers.remove(phone);
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
	 * @return a.
	 */
	public List<String> getPhonenumbers() {
		return phonenumbers;
	}

	/**
	 * asd.
	 * @param phonenumbers a.
	 */
	public void setPhonenumbers(List<String> phonenumbers) {
		this.phonenumbers = phonenumbers;
	}

	/**
	 * asd.
	 * @param contact ads.
	 * @return asd.
	 */
	public static ContactDto create(Contact contact) {
		ContactDto c = new ContactDto();
		c.setDetails(contact.getDetails());
		c.setLabel(contact.getLabel());
		c.setId(contact.getId());
		
		for (Email s : contact.getEmails()) {
			c.addEmail(s.getContent());
		}
		
		for (PhoneNumber s : contact.getPhonenumbers()) {
			c.addPhoneNumber(s.getContent());
		}
		
		return c;
	}
	
	/**
	 * asd.
	 */
	public ContactDto() {
		
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
	
	

}
