package telephony.core.service.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleDto {

    private Long id;
    private String label;
    private String dateOut;
    private Long storeId;
    private Long contactId;

    private List<ProductAddDto> products;

    public SaleDto() {
        products = new ArrayList<ProductAddDto>();
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

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
        this.dateOut = sdf.format(dateOut);
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
