package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * asd.
 */
@Embeddable
public class Email {
	
	@Column(name = "content", nullable = false, length = 100)
	private String content;

	/**
	 * asd.
	 */
	public Email() {
	
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
