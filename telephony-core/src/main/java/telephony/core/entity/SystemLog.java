package telephony.core.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// TODO : delete this entity and its dependencies

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity
@Table(name = "system_logs")
public class SystemLog extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_logs_seq")
    @SequenceGenerator(
        name = "system_logs_seq",
        sequenceName = "system_logs_seq",
        allocationSize = 1)
    private Long id;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "label", nullable = false, length = 100)
    private String label;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    /**
     * asd.
     */
    public SystemLog() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * asd.
     * @return asd.
     */
    public String getContent() {
        return content;
    }

    /**
     * asd.
     * @param content asd.
     */
    public void setContent(String content) {
        this.content = content;
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

    /**
     * asd.
     * @return asd.
     */
    public String getType() {
        return type;
    }

    /**
     * asd.
     * @param type asd.
     */
    public void setType(String type) {
        this.type = type;
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

        SystemLog systemLog = (SystemLog) o;

        if (!content.equals(systemLog.content)) {
            return false;
        }
        if (!id.equals(systemLog.id)) {
            return false;
        }
        if (!label.equals(systemLog.label)) {
            return false;
        }
        if (!type.equals(systemLog.type)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + label.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
