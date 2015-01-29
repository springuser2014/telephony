package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryAddDto {

    private String label;
    private Date dateIn;
    private Long storeId;
    private Long contactId;

    private List<ProductAddDto> products;

    public DeliveryAddDto() {
        this.products = new ArrayList<ProductAddDto>();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public List<ProductAddDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAddDto> products) {
        this.products = products;
    }

    public void addProduct(ProductAddDto product) {
        if (!products.contains(product)) {
            products.add(product);
        }
    }

    public void removeProduct(ProductAddDto product) {
        if (products.contains(product)) {
            products.remove(product);
        }
    }
}
