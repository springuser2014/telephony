package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryAddDto
extends AbstractDeliveryDto {

    private List<ProductAddDto> products;

    public DeliveryAddDto() {
        this.products = new ArrayList<>();
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
