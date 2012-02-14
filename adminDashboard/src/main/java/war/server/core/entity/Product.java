package war.server.core.entity;


import war.server.core.entity.common.BaseEntity;
import war.server.core.entity.common.Money;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product extends BaseEntity implements Serializable {

    @Column(name = "imei", nullable = false, length = 15)
    private String imei;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id" , nullable = false)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id" , nullable = true)
    private Sale sale;

    @Column(name = "producer" , length = 100, nullable = false)
    private String producer;

    @Column(name = "model" , length = 100, nullable = false)
    private String model;

    @Column(name = "color" , length = 20, nullable = false)
    private String color;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status" , nullable = false)
//    private ProductStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column=@Column(name = "price_in", nullable = false))
    })
    private Money priceIn = new Money();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column=@Column(name = "price_out", nullable = true))
    })
    private Money priceOut = new Money();

    public Product() {}


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

//    public ProductStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(ProductStatus status) {
//        this.status = status;
//    }

    public Money getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Money priceIn) {
        this.priceIn = priceIn;
    }

    public Money getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(Money priceOut) {
        this.priceOut = priceOut;
    }
}
