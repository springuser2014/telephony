package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleDto {

    private Long id;
    private String label;
    private Date dateOut;
    private Long storeId;
    private Long contactId;

    private List<ProductAddDto> products;

    public SaleDto() {
        products = new ArrayList<>();
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
