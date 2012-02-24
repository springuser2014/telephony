package war.server.core.entity.common;

import com.google.gwt.user.client.rpc.IsSerializable;
import net.sf.gilead.pojo.gwt.LightEntity;

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

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "edited_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editedAt;

    @Column(name = "edited_by")
    private Long editedBy;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "deleted_by")
    private Long deletedBy;

    public BaseEntity() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(getClass().getName());
        str.append("[id=");
        str.append(id);
        str.append("]");

        return str.toString();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (id == null)
            return 0;
        else
            return id.hashCode();
    }


    /**
     * Zwraca klucz/identyfikator obiektu.
     *
     * @return Identyfikator obiektu lub null jeżeli nie posiada on tożsamości.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Ustawia identyfikator obiektu.
     *
     * @param id nowa wartość identyfikatora.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Zwraca nr wersji obiektu.
     * Protected - na potrzeby Gilead.
     *
     * @return wersja obiektu.
     */
    protected int getVersion() {
        return this.version;
    }

    /**
     * Ustawia nr wersji obiektu.
     * Protected - na potrzeby Gilead.
     *
     * @param version nowa wersja obiektu.
     */
    protected void setVersion(int version) {
        this.version = version;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

    public Long getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(Long editedBy) {
        this.editedBy = editedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }
}

