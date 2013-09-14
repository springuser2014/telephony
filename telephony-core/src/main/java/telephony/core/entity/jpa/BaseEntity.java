package telephony.core.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Basic entity.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

    @SuppressWarnings("unused")
	private static final long serialVersionUID = -3858014970182092169L;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User creator;

    /**
     * Do nothing.
     */
    public BaseEntity() {

    }

    /**
     * Return basic properties of the entity.
     * @return An entity description.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[id=");
        str.append(getId());
        str.append(",created_at=");
        str.append(getCreatedAt());
        str.append(",created_by");
        str.append(getCreator());
        str.append("]");

        return str.toString();
    }

    /**
     * Compares object with another one. 
     * The comparison bases on entity's primary key. 
     * @param obj to compare with.
     * @return result of the comparison.
     */
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

    /**
     * Generates hashCode for given entity.
     * @return A number.
     */
    @Override
    public int hashCode() {
        if (getId() == null) {
            return 0;
        } else {
            return getId().hashCode();
        }
    }

    /**
     * Gets entity's primary key.
     * @return asd.
     */
    public abstract Long getId();

    /**
     * Sets entity's primary key.
     * @param id Entity's primary key.
     */
    public abstract void setId(Long id);

    /**
     * Gets entity creation date.
     * @return creation date.
     */
    public final Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets entity's creation date.
     * @param createdAt creation date.
     */
    public final void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets entity's creator.
     * @return creator.
     */
    public final User getCreator() {
        return creator;
    }

    /**
     * Sets entity's creator.
     * @param creator creator.
     */
    public final void setCreator(User creator) {
        this.creator = creator;
    }
}

