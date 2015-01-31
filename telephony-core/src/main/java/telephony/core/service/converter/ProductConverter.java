package telephony.core.service.converter;

import com.google.inject.Inject;
import org.apache.commons.collections.CollectionUtils;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.*;

import java.util.*;

public class ProductConverter {

    @Inject
    DeliveryConverter deliveryConverter;

    @Inject
    SaleConverter saleConverter;

    @Inject
    ProductComplaintConverter productComplaintConverter;

    @Inject
    SaleComplaintConverter saleComplaintConverter;

    @Inject
    SaleComplaintDao saleComplaintDao;

    @Inject
    ProductComplaintDao productComplaintDao;

    public ProductAddDto toProductDto(Product product) {

        ProductAddDto p = new ProductAddDto();
        p.setColor(product.getColor());
        p.setImei(product.getImei());
        p.setModel(product.getModel().getLabel());
        p.setProducer(product.getModel().getProducer().getLabel());
        if (product.getCurrentTax() != null) {

            ProductTaxDto productTaxDto = new ProductTaxDto();
            productTaxDto.setFrom(product.getCurrentTax().getFrom());
            productTaxDto.setTo(product.getCurrentTax().getTo());
            productTaxDto.setTaxId(product.getCurrentTax().getTax().getId());

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

    public ProductEditDto toProductEditDto(Product p) {
        ProductEditDto b = new ProductEditDto();

        b.setId(p.getId());
        b.setColor(p.getColor());
        b.setImei(p.getImei());
        b.setPriceIn(p.getPriceIn());
        b.setCurrentPrice(getCurrentPriceDto(p.getCurrentPricing()));
        b.setProductTax(getProductTaxDto(p.getCurrentTax()));
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

    private ProductTaxAddDto getProductTaxDto(ProductTax currentTax) {
        ProductTaxAddDto dto = new ProductTaxAddDto();
        dto.setFrom(currentTax.getFrom());
        dto.setTaxId(currentTax.getTax().getId());

        return dto;
    }

    private PricingAddDto getCurrentPriceDto(Pricing pricing) {
        PricingAddDto dto = new PricingAddDto();

        dto.setFrom(pricing.getFrom());
        dto.setRate(pricing.getRate());

        return dto;
    }

    public ProductDetailsDto toProductDetailsDto(Product product) {
        ProductDetailsDto dto = new ProductDetailsDto();

        dto.setColor(product.getColor());
        dto.setDeliveryId(product.getDelivery().getId());
        dto.setImei(product.getImei());
        dto.setPriceIn(product.getPriceIn());
        dto.setPrice(product.getCurrentPricing().getRate());
        dto.setTax(product.getCurrentTax().getTax().getRate());
        dto.setModel(product.getModel().getLabel());
        dto.setProducer(product.getModel().getProducer().getLabel());

        if (product.getSale() != null) {
            dto.setSaleId(product.getSale().getId());
        } else {
            dto.setSaleId(null);
        }

        dto.setId(product.getId());

        if (product.getDelivery() != null) {
            dto.setDelivery(deliveryConverter.toDeliverySearchDto(product.getDelivery()));
        }

        Collection<Long> ids = new ArrayList<>();
        ids.add(product.getId());
        Collection<ProductComplaint> productComplaints = productComplaintDao.findByProductsIds(ids);

        if (productComplaints != null) {
            for (ProductComplaint pc : productComplaints) {
                dto.addProductComplaint(productComplaintConverter.toProductComplaintDto(pc));
            }
        }

        if (product.getSale() != null) {
            dto.setSale(saleConverter.toSaleSearchDto(product.getSale()));

            Collection<Long> ids2 = new ArrayList<>();
            ids2.add(product.getSale().getId());
            Collection<SaleComplaint> complaints = saleComplaintDao.findBySalesIds(ids2);

            if (complaints != null){
                for(SaleComplaint pc : complaints) {
                    dto.addSaleComplaint(saleComplaintConverter.toSaleComplaintDto(pc));
                }
            }
        }

        return dto;
    }
}
