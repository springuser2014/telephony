package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stores")
public class Store extends BaseEntity implements Serializable {

    @Column(name = "label", nullable = false)
    private String label;

    public Store() {}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
