package telephony.server.core.entity;


import javax.persistence.*;
import telephony.server.core.entity.common.BaseEntity;
import telephony.server.core.entity.common.Money;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(name = "imei", nullable = false, length = 100)
    private String imei;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sale_id", nullable = true)
    private Sale sale;

    @Column(name = "producer" , length = 100, nullable = false)
    private String producer;

    @Column(name = "model" , length = 100, nullable = false)
    private String model;

    @Column(name = "color" , length = 20, nullable = false)
    private String color;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column=@Column(name = "price_in", nullable = true))
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Product product = (Product) o;

        if (color != null ? !color.equals(product.color) : product.color != null) return false;
        if (delivery != null ? !delivery.equals(product.delivery) : product.delivery != null) return false;
        if (imei != null ? !imei.equals(product.imei) : product.imei != null) return false;
        if (model != null ? !model.equals(product.model) : product.model != null) return false;
        if (priceIn != null ? !priceIn.equals(product.priceIn) : product.priceIn != null) return false;
        if (priceOut != null ? !priceOut.equals(product.priceOut) : product.priceOut != null) return false;
        if (producer != null ? !producer.equals(product.producer) : product.producer != null) return false;
        if (sale != null ? !sale.equals(product.sale) : product.sale != null) return false;
        if (store != null ? !store.equals(product.store) : product.store != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (imei != null ? imei.hashCode() : 0);
        result = 31 * result + (store != null ? store.hashCode() : 0);
        result = 31 * result + (delivery != null ? delivery.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (priceIn != null ? priceIn.hashCode() : 0);
        result = 31 * result + (priceOut != null ? priceOut.hashCode() : 0);
        return result;
    }
}
