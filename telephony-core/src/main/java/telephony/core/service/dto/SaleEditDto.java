package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleEditDto {

    private Long saleId;
    private String label;
    private Date dateOut;
    private Long storeId;
    private Long contactId;

    private List<Long> productsToAdd;
    private List<Long> productsToRemove;

    public SaleEditDto() {
        productsToAdd = new ArrayList<Long>();
        productsToRemove = new ArrayList<Long>();
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
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
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
//        this.dateOut = sdf.format(dateOut);
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

    public List<Long> getProductsToAdd() {
        return productsToAdd;
    }

    public void setProductsToAdd(List<Long> productsToAdd) {
        this.productsToAdd = productsToAdd;
    }

    public void addProductToAdd(Long productToAdd) {

        if (!productsToAdd.contains(productToAdd)) {
            productsToAdd.add(productToAdd);
        }
    }

    public void removeProductToAdd(Long productToAdd) {

        if (productsToAdd.contains(productToAdd)) {
            productsToAdd.remove(productToAdd);
        }
    }

    public List<Long> getProductsToRemove() {
        return productsToRemove;
    }

    public void setProductsToRemove(List<Long> productsToRemove) {
        this.productsToRemove = productsToRemove;
    }

    public void addProductToRemove(Long productToRemove) {

        if (!productsToRemove.contains(productToRemove)) {
            productsToRemove.add(productToRemove);
        }
    }

    public void removeProductToRemove(Long productToRemove) {

        if (productsToRemove.contains(productToRemove)) {
            productsToRemove.remove(productToRemove);
        }
    }
}
