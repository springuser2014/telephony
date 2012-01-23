package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "deliveries")
public class Delivery extends BaseEntity implements Serializable {

    @Column(name = "label", nullable =false)
    private String label;

    public Delivery() {}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
