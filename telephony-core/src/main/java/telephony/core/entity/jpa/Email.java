package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
public class Email {
	
	@Column(name = "content", nullable = false, length = 100)
	private String content;

	public Email() { }

	public Email(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Email email = (Email) o;

		if (content != null ? !content.equals(email.content) : email.content != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return content != null ? content.hashCode() : 0;
	}

}
