package telephony.core.service.converter;

import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.ProductDto;

public final class ProductConverter {

    public static ProductDto toProductDto(Product product) {

        ProductDto p = new ProductDto();
        p.setColor(product.getColor());
        p.setImei(product.getImei());
        p.setModel(product.getModel().getLabel());
        p.setProducer(product.getModel().getProducer().getLabel());
        if (product.getCurrentTax() != null) {
            p.setTaxFrom(product.getCurrentTax().getFrom());
            p.setTaxTo(product.getCurrentTax().getTo());
            p.setTaxId(product.getCurrentTax().getTax().getId());
        }

        p.setPriceIn(product.getPriceIn());

        if (product.getCurrentPricing() != null) {
            p.setPriceFrom(product.getCurrentPricing().getFrom());
            p.setPriceTo(product.getCurrentPricing().getTo());
            p.setCurrentPrice(product.getCurrentPricing().getRate());
        }

        return p;
    }
}
