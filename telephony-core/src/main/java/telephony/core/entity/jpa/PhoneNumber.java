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

	@Column(name = "prefix", nullable = false, length = 10)
	private String prefix;
	
	/**
	 * ad.
	 */
	public PhoneNumber() {
	
	}

	public PhoneNumber(String prefix, String content) {
		this.prefix = prefix;
		this.content = content;
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

	/**
	 * asd.
	 * @return a.
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * asd.
	 * @param prefix a.
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PhoneNumber that = (PhoneNumber) o;

		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = content != null ? content.hashCode() : 0;
		result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
		return result;
	}
}
