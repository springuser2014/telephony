package telephony.core.entity.jpa;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntity {

    @SuppressWarnings("unused")
	private static final long serialVersionUID = -3858014970182092169L;

    public BaseEntity() {

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[id=");
        str.append(getId());
        str.append("]");

        return str.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof BaseEntity)) {
            return false;
        }

        final BaseEntity other = (BaseEntity) obj;
        if (this.getId() != null && other.getId() != null) {
            return this.getId().equals(other.getId());
        }

        return false;
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return 0;
        } else {
            return getId().hashCode();
        }
    }

    public abstract Long getId();

    public abstract void setId(Long id);
}

