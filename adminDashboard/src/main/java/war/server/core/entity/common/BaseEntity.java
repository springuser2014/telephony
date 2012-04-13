package war.server.core.entity.common;

import com.google.gwt.user.client.rpc.IsSerializable;
import net.sf.gilead.pojo.gwt.LightEntity;
import war.server.core.entity.User;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public abstract class BaseEntity extends LightEntity implements IsSerializable {

    private static final long serialVersionUID = -3858014970182092169L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="base_entity_seq")
    @SequenceGenerator(name="base_entity_seq", sequenceName="base_entity_seq", allocationSize=1)
    private Long id;

    @Version
    @Column(name = "version")
    private int version = 0;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by"  ,nullable = false)
    private User creator;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edited_by" , nullable = true)
    private User editor;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deleted_by", nullable = true)
    private User deleter;

    public BaseEntity() { }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[id=");
        str.append(id);
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
        if (this.id != null && other.id != null)
            return this.id.equals(other.id);

        return false;
    }

    @Override
    public int hashCode() {
        if (id == null)
            return 0;
        else
            return id.hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected int getVersion() {
        return this.version;
    }

    protected void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
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

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public User getDeleter() {
        return deleter;
    }

    public void setDeleter(User deleter) {
        this.deleter = deleter;
    }
}

