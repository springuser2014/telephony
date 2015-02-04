package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleAddDto {

    private Long id;
    private String label;
    private Date dateOut;
    private Long storeId;
    private Long contactId;

    private List<Long> productsIds;

    public SaleAddDto() {
        productsIds = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
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

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProducts(List<Long> products) {
        this.productsIds = products;
    }

    public void addProductId(Long productId) {
        if (!productsIds.contains(productId)) {
            productsIds.add(productId);
        }
    }

    public void removeProductId(Long productId) {
        if (productsIds.contains(productId)) {
            productsIds.remove(productId);
        }
    }

}
