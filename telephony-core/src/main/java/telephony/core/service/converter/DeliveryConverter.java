package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.*;
import telephony.core.service.exception.DeliveryServiceException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class DeliveryConverter {

    @Inject
    ProductConverter productConverter;

    @Inject
    ModelDao modelDao;

    @Inject
    ProducerDao producerDao;

    @Inject
    StoresDao storesDao;

    @Inject
    ContactsDao contactsDao;

    @Inject
    ProductsDao productsDao;

    @Inject
    TaxDao taxDao;

    @Inject
    ProductTaxDao productTaxDao;


    public DeliveryDto toDeliveryDto(Delivery delivery) {

        DeliveryDto bean = new DeliveryDto();
        bean.setContactId(delivery.getContact().getId());
        bean.setDateIn(delivery.getDateIn());
        bean.setStoreId(delivery.getStore().getId());
        bean.setId(delivery.getId());
        bean.setLabel(delivery.getLabel());

        List<ProductAddDto> products = new ArrayList<ProductAddDto>();

        if (delivery.getProducts() == null) {
            delivery.setProducts(new ArrayList<Product>());
        }

        for (Product prod : delivery.getProducts()) {
            ProductAddDto p = productConverter.toProductDto(prod);
            products.add(p);
        }

        bean.setProducts(products);

        return bean;
    }

    public Delivery toEntity(DeliveryAddDto addDto) throws DeliveryServiceException {
        // TODO cleanup this mess
        Delivery delivery = new Delivery();

        Store store = storesDao.findById(addDto.getStoreId());
        Contact contact = contactsDao.findById(addDto.getContactId());

        delivery.setDateIn(addDto.getDateIn());
        delivery.setLabel(addDto.getLabel());
        delivery.setStore(store);
        delivery.setContact(contact);

        Collection<Product> products = new ArrayList<Product>();

        for (ProductAddDto dto : addDto.getProducts()) {

            Model model = modelDao.findByLabel(dto.getModel());
            Producer producer = producerDao.findByLabel(dto.getProducer());

            Product p = toProduct(dto, delivery, store, model);

            if (model != null && producer != null && model.getProducer().equals(producer)) {
                p.setModel(model);
            } else if (model != null && producer != null && !model.getProducer().equals(producer)) {
                throw new DeliveryServiceException();
            } else if (model == null && producer != null) {
                model = new Model();
                model.setLabel(dto.getModel());
                model.setProducer(producer);

                model = modelDao.saveOrUpdate(model);
            } else if (model != null && producer == null) {
                throw new DeliveryServiceException();
            } else {
                producer = new Producer();
                producer.setLabel(dto.getProducer());
                producer = producerDao.saveOrUpdate(producer);

                model = new Model();
                model.setLabel(dto.getModel());
                model.setProducer(producer);
                model = modelDao.saveOrUpdate(model);
            }

            PricingDto pdto = dto.getCurrentPrice();

            Pricing pr = new Pricing();
            pr.setFrom(pdto.getFrom());
            pr.setTo(pdto.getTo());
            pr.setRate(pdto.getRate());
            pr.setProduct(p);
            List<Pricing> pricings = new ArrayList<>();
            pricings.add(pr);
            p.setPricings(pricings);

            p.setModel(model);
            productsDao.save(p);
            p = productsDao.findById(p.getId());

            Tax tax = taxDao.findById(dto.getProductTax().getId());

            ProductTax productTax = new ProductTax();
            productTax.setProduct(p);
            productTax.setTax(tax);
            productTax.setFrom(new Date());
            productTax.setTo(null);

            List<ProductTax> pts = new ArrayList<ProductTax>();
            pts.add(productTax);
            p.setProductTaxes(pts);

            productTaxDao.save(productTax);

            if (p != null ) {
                products.add(p);
            }

            productsDao.saveOrUpdate(p);
        }

        return delivery;
    }

    // TODO : extract to converter
    private Product toProduct(ProductAddDto bean, Delivery delivery, Store store, Model model) {

        Product p = new Product();
        p.setColor(bean.getColor());
        p.setImei(bean.getImei());
        p.setPriceIn(bean.getPriceIn());
        p.setStore(store);
        p.setModel(model);
        p.setDelivery(delivery);

        return p;
    }

    public void updateEntity(Delivery delivery, DeliveryEditDto editDto) {

    }
}
