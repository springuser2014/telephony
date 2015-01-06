package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class ProductComplaintFilterCriteria extends ComplaintFilterCriteria {

    List<Long> productsIds;

    public ProductComplaintFilterCriteria() {
        productsIds = new ArrayList<Long>();
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
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
