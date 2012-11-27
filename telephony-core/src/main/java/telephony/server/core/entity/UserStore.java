package telephony.server.core.entity;

import telephony.server.core.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_stores")
public class UserStore extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_stores_seq")
    @SequenceGenerator(name = "user_stores_seq", sequenceName = "user_stores_seq", allocationSize = 1)
    private Long id;

    public UserStore() {}
}
