package telephony.core.entity.common;

import com.google.gwt.user.client.rpc.IsSerializable;
import telephony.core.entity.User;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity implements IsSerializable {

    private static final long serialVersionUID = -3858014970182092169L;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    protected User creator;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date deletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by", nullable = true)
    protected User deleter;

    public BaseEntity() {}

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
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof BaseEntity))
            return false;

        final BaseEntity other = (BaseEntity) obj;
        if (this.getId() != null && other.getId() != null)
            return this.getId().equals(other.getId());

        return false;
    }

    @Override
    public int hashCode() {
        if (getId() == null)
            return 0;
        else
            return getId().hashCode();
    }

    public abstract Long getId();

    public abstract void setId(Long id);

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getDeleter() {
        return deleter;
    }

    public void setDeleter(User deleter) {
        this.deleter = deleter;
    }
}

