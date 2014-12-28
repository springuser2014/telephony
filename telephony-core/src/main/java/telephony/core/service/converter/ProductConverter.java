package telephony.core.service.converter;

import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.ProductDto;
import telephony.core.service.dto.ProductSearchDto;

public class ProductConverter {

    public ProductDto toProductDto(Product product) {

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

    public ProductSearchDto toProductSearchDto(Product p) {
        ProductSearchDto b = new ProductSearchDto();

        b.setColor(p.getColor());
        b.setDeliveryId(p.getDelivery().getId());
        b.setImei(p.getImei());
        b.setPriceIn(p.getPriceIn());
        b.setPrice(p.getCurrentPricing().getRate());
        b.setTax(p.getCurrentTax().getTax().getRate());
        b.setModel(p.getModel().getLabel());
        b.setProducer(p.getModel().getProducer().getLabel());

        if (p.getSale() != null) {
            b.setSaleId(p.getSale().getId());
        } else {
            b.setSaleId(null);
        }

        b.setId(p.getId());

        return b;
    }
}
