
package telephony.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * asd.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 */
@Entity
@Table(name = "user_stores")
public class UserStore extends BaseEntity {

    /**
     * asd.
     */
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_stores_seq")
    @SequenceGenerator(
        name = "user_stores_seq",
        sequenceName = "user_stores_seq",
        allocationSize = 1)
    private Long id;

    /**
     * asd.
     */
    public UserStore() {

    }

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final Long getId() {

        return this.id;
    }

    /**
     * asd.
     * @param iD asd.
     */
    @Override
    public final void setId(final Long iD) {

        this.id = iD;
    }
}
