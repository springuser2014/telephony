package telephony.server.core.entity;

import telephony.server.core.entity.common.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "system_logs")
public class SystemLog extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_logs_seq")
    @SequenceGenerator(name = "system_logs_seq", sequenceName = "system_logs_seq", allocationSize = 1)
    private Long id;


    @Column(name = "label", nullable = false, length = 5000)
    private String label;


    private String type;

    public SystemLog() {}
}
