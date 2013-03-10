package telephony.core.entity.jpa;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity
@Table(name = "stores")
public class Store extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stores_seq")
    @SequenceGenerator(name = "stores_seq", sequenceName = "stores_seq", allocationSize = 1)
    private Long id;

    @Column(name = "label", nullable = false, length = 255)
    private String label;

    /**
     * asd.
     */
    public Store() {

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getLabel() {
        return label;
    }

    /**
     * asd.
     * @param label asd.
     */
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Store store = (Store) o;

        if (label != null ? !label.equals(store.label) : store.label != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        return result;
    }

}
