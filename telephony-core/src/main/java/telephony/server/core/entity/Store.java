package telephony.server.core.entity;


import telephony.server.core.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stores_seq")
    @SequenceGenerator(name = "stores_seq", sequenceName = "stores_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false)
    private String label;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Store store = (Store) o;

        if (label != null ? !label.equals(store.label) : store.label != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

    public Store() {
    }
}
