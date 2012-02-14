package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity implements Serializable {

    public Sale() {}

    @Column(name = "label", nullable =false)
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
