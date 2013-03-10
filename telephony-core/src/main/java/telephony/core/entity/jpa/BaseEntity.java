package telephony.core.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements IsSerializable {

    @SuppressWarnings("unused")
	private static final long serialVersionUID = -3858014970182092169L;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User creator;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deleter;


    /**
     * asd.
     */
    public BaseEntity() {

    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[id=");
        str.append(getId());
        str.append("]");

        return str.toString();
    }

    /**
     * asd.
     * @param obj asd.
     * @return asd.
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
     * asd.
     * @return asd.
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
     * asd.
     * @return asd.
     */
    public abstract Long getId();

    /**
     * asd.
     * @param id asd.
     */
    public abstract void setId(Long id);

    /**
     * asd.
     * @return asd.
     */
    public final Date getCreatedAt() {
        return createdAt;
    }

    /**
     * asd.
     * @param createdAt asd.
     */
    public final void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * asd.
     * @return asd.
     */
    public final Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * asd.
     * @param deletedAt asd.
     */
    public final void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    /**
     * asd.
     * @return asd.
     */
    public final User getCreator() {
        return creator;
    }

    /**
     * asd.
     * @param creator asd.
     */
    public final void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * asd.
     * @return asd.
     */
    public final User getDeleter() {
        return deleter;
    }

    /**
     * asd.
     * @param deleter asd.
     */
    public final void setDeleter(User deleter) {
        this.deleter = deleter;
    }
}

