package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Fax {

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    public Fax() { }

    public Fax(String content) {
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

        Fax fax = (Fax) o;

        if (content != null ? !content.equals(fax.content) : fax.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }
}
