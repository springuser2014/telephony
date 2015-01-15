package telephony.core.service.converter;

import telephony.core.entity.jpa.Product;
import telephony.core.service.dto.PricingDto;
import telephony.core.service.dto.ProductAddDto;
import telephony.core.service.dto.ProductFetchDto;
import telephony.core.service.dto.ProductTaxDto;

public class ProductConverter {

    public ProductAddDto toProductDto(Product product) {

        ProductAddDto p = new ProductAddDto();
        p.setColor(product.getColor());
        p.setImei(product.getImei());
        p.setModel(product.getModel().getLabel());
        p.setProducer(product.getModel().getProducer().getLabel());
        if (product.getCurrentTax() != null) {

            ProductTaxDto productTaxDto = new ProductTaxDto();
            productTaxDto.setTaxFrom(product.getCurrentTax().getFrom());
            productTaxDto.setTaxTo(product.getCurrentTax().getTo());
            productTaxDto.setId(product.getCurrentTax().getTax().getId());

            p.setProductTax(productTaxDto);
        }

        p.setPriceIn(product.getPriceIn());

        if (product.getCurrentPricing() != null) {
            PricingDto dto = new PricingDto();
            dto.setFrom(product.getCurrentPricing().getFrom());
            dto.setTo(product.getCurrentPricing().getTo());
            dto.setRate(product.getCurrentPricing().getRate());

            p.setCurrentPrice(dto);
        }

        return p;
    }

    public ProductFetchDto toProductFetchDto(Product p) {
        ProductFetchDto b = new ProductFetchDto();

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
