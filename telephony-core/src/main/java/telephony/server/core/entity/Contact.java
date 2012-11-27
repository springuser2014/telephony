package telephony.server.core.entity;

import telephony.server.core.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_seq")
    @SequenceGenerator(name = "contacts_seq", sequenceName = "contacts_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false, length = 100)
    private String label;

    @Column(name = "content", nullable = false, length = 5000)
    private String details;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    public Contact() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
