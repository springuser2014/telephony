package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * asd.
 */
@Embeddable
public class PhoneNumber {
	
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
