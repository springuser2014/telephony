package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "deliveries")
public class Delivery extends BaseEntity {

    @Column(name = "label", nullable =false)
    private String label;

    @Column(name = "date_in")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateIn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "delivery", fetch=FetchType.EAGER)
    private Collection<Product> products;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Collection<Product> getProducts() {
        return products;
    }
    
    public void addProduct(Product product) {
        products.add(product);
        product.setDelivery(this);
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Delivery() {}

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
