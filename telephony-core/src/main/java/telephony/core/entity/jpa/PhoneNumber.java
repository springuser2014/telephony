package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

/**
 * asd.
 */
@Embeddable
public class PhoneNumber {
	
//	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contact_id")
//    private Contact contact;	
	
	@Column(name = "content", nullable = false, length = 100)
	private String content;
	
	/**
	 * ad.
	 */
	public PhoneNumber() {
	
	}

	/**
	 * ad.
	 * @return a.
	 */
//	public Contact getContact() {
//		return contact;
//	}

	/**
	 * ad.
	 * @param contact a.
	 */
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}

	/**
	 * ad.
	 * @return a.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * ad.
	 * @param content a.
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
