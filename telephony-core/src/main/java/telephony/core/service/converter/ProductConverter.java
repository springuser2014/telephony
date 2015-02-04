package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.*;
import telephony.core.service.exception.ProductServiceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static telephony.core.assertion.CommonAssertions.*;

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

    @Inject
    StoresDao storesDao;

    @Inject
    DeliveriesDao deliveriesDao;

    @Inject
    ModelDao modelDao;

    @Inject
    ProducerDao producerDao;

    @Inject
    TaxDao taxDao;

    @Inject
    ProductTaxDao productTaxDao;

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
        b.setStoreId(p.getStore().getId());

        if (p.getSale() != null) {
            b.setSaleId(p.getSale().getId());
        } else {
            b.setSaleId(null);
        }

        b.setId(p.getId());

        boolean editable = isNull(p.getSale());
        boolean deletable = (
            isEmpty(p.getComplaints()) &&
            isNull(p.getSale())
        );

        b.setDeletable(deletable);
        b.setEditable(editable);

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

    public void updateEntity(Product product, ProductEditDto editingProduct) throws ProductServiceException {

        // TODO cleanup this mess

        if (editingProduct.getColor() != null) {
            product.setColor(editingProduct.getColor());
        }
        if (editingProduct.getImei() != null) {
            product.setImei(editingProduct.getImei());
        }

        if (editingProduct.getPriceIn() != null) {
            product.setPriceIn(editingProduct.getPriceIn());
        }

        if (editingProduct.getColor() != null) {
            product.setColor(editingProduct.getColor());
        }

        if (editingProduct.getImei() != null) {
            product.setImei(editingProduct.getImei());
        }

        if (editingProduct.getPriceIn() != null) {
            product.setPriceIn(editingProduct.getPriceIn());
        }

        Model model = null;
        Producer producer = null;

        model = modelDao.findByLabel(editingProduct.getModel());
        producer = producerDao.findByLabel(editingProduct.getProducer());

        if (model != null && producer != null && model.getProducer().equals(producer)) {
            product.setModel(model);
        } else if (model != null && producer != null && !producer.equals(model.getProducer())) {
            throw new ProductServiceException();
        } else if (model == null && producer != null) {
            model = new Model();
            model.setLabel(editingProduct.getModel());
            model.setProducer(producer);

            model = modelDao.saveOrUpdate(model);
        } else if (model != null && producer == null) {
            throw new ProductServiceException();
        } else {
            producer = new Producer();
            producer.setLabel(editingProduct.getProducer());
            producer = producerDao.saveOrUpdate(producer);

            model = new Model();
            model.setLabel(editingProduct.getModel());
            model.setProducer(producer);
            model = modelDao.saveOrUpdate(model);
        }

        product.setModel(model);
        Date d = new Date();

        if (product.getCurrentPricing() != null && editingProduct.getProductTax() != null) {

            if (product.getCurrentPricing().getRate() != editingProduct.getCurrentPrice().getRate()) {

                Pricing currPricing = product.getCurrentPricing();
                currPricing.setTo(d);

                Pricing price = new Pricing();
                price.setProduct(product);
                price.setRate(editingProduct.getCurrentPrice().getRate());
                price.setFrom(d);
                price.setTo(null);

                product.addPricing(price);
            }
        }

        if (product.getCurrentTax() != null && editingProduct.getProductTax() != null) {
            if (product.getCurrentTax().getId() != editingProduct.getProductTax().getTaxId()) {

                ProductTax currProductTax = product.getCurrentTax();
                currProductTax.setTo(d);

                Tax tax = taxDao.findById(editingProduct.getProductTax().getTaxId());

                ProductTax productTax = new ProductTax();
                productTax.setProduct(product);
                productTax.setTax(tax);
                productTax.setFrom(d);
                productTax.setTo(null);

                productTaxDao.save(productTax);
            }
        }
    }
}
