package telephony.core.service.converter;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.DeliveryDto;
import telephony.core.service.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

public final class DeliveryConverter {

    public static DeliveryDto toDeliveryDto(Delivery delivery) {

        DeliveryDto bean = new DeliveryDto();
        bean.setContactId(delivery.getContact().getId());
        bean.setDateIn(delivery.getDateIn());
        bean.setStoreId(delivery.getStore().getId());
        bean.setId(delivery.getId());
        bean.setLabel(delivery.getLabel());

        List<ProductDto> products = new ArrayList<ProductDto>();

        if (delivery.getProducts() == null) {
            delivery.setProducts(new ArrayList<Product>());
        }

        for (Product prod : delivery.getProducts()) {
            ProductDto p = ProductConverter.toProductDto(prod);
            products.add(p);
        }

        bean.setProducts(products);

        return bean;
    }
}
