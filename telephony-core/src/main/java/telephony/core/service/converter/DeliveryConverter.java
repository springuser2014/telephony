package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.*;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.*;
import telephony.core.service.exception.DeliveryServiceException;

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

    @Inject
    PricingsDao pricingsDao;

    public DeliveryDto toDeliveryDto(Delivery delivery) {

        DeliveryDto bean = new DeliveryDto();
        bean.setContactId(delivery.getContact().getId());
        bean.setDateIn(delivery.getDateIn());
        bean.setStoreId(delivery.getStore().getId());
        bean.setId(delivery.getId());
        bean.setLabel(delivery.getLabel());

        List<ProductEditDto> products = new ArrayList<>();

        if (delivery.getProducts() == null) {
            delivery.setProducts(new ArrayList<Product>());
        }

        for (Product prod : delivery.getProducts()) {
            ProductEditDto p = productConverter.toProductEditDto(prod);
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

            PricingAddDto pdto = dto.getCurrentPrice();

            Pricing pr = new Pricing();
            pr.setFrom(pdto.getFrom());
            pr.setTo(null);
            pr.setRate(pdto.getRate());
            pr.setProduct(p);
            List<Pricing> pricings = new ArrayList<>();
            pricings.add(pr);
            p.setPricings(pricings);

            p.setModel(model);
            productsDao.save(p);
            p = productsDao.findById(p.getId());

            Tax tax = taxDao.findById(dto.getProductTax().getTaxId());

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

    public void updateEntity(Delivery delivery, DeliveryEditDto editDto) throws DeliveryServiceException {

        // TODO cleanup this mess

        Store store = null;

        if (editDto.getStoreId() != null) {
            store = storesDao.findById(editDto.getStoreId());
        } else {
            store = delivery.getStore();
        }

        delivery.setStore(store);

        Contact contact = null;
        if (editDto.getContactId() != null) {
            contact = contactsDao.findById(editDto.getContactId());
        } else {
            contact = delivery.getContact();
        }

        delivery.setContact(contact);

        for (Long id : editDto.getProductsToDelete()) { // TODO : to improve
            productsDao.removeById(id);
        }

        Collection<Product> products = new ArrayList<>();

        for (ProductAddDto dto : editDto.getProductsToAdd()) {

            Model model = null;
            Producer producer = null;

            model = modelDao.findByLabel(dto.getModel());
            producer = producerDao.findByLabel(dto.getProducer());

            Product product = toProduct(dto, delivery, store, model);

            if (model != null && producer != null && model.getProducer().equals(producer)) {
                product.setModel(model);
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

            product.setModel(model);
            productsDao.save(product);
            product = productsDao.findById(product.getId());

            Pricing price = new Pricing();
            price.setFrom(dto.getCurrentPrice().getFrom());
            price.setTo(null);
            price.setRate(dto.getCurrentPrice().getRate());

            product.setPriceIn(dto.getPriceIn());

            if (product.getPricings() == null) {
                product.setPricings(new ArrayList<Pricing>());
            }
            price.setProduct(product);
            pricingsDao.save(price);

            Tax tax = taxDao.findById(dto.getProductTax().getTaxId());

            ProductTax productTax = new ProductTax();
            productTax.setProduct(product);
            productTax.setTax(tax);
            productTax.setFrom(dto.getProductTax().getFrom());
            productTax.setTo(null);

            productTaxDao.save(productTax);

            if (product != null ) {
                products.add(product);
            }
        }

        for (ProductEditDto editingProduct : editDto.getProductsToEdit()) {

            Product product = productsDao.findById(editingProduct.getId());

            if (editingProduct.getColor() != null) {
                product.setColor(editingProduct.getColor());
            }
            if (editingProduct.getImei() != null) {
                product.setImei(editingProduct.getImei());
            }

            if (editingProduct.getPriceIn() != null) {
                product.setPriceIn(editingProduct.getPriceIn());
            }

            product.setStore(store);

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
                throw new DeliveryServiceException();
            } else if (model == null && producer != null) {
                model = new Model();
                model.setLabel(editingProduct.getModel());
                model.setProducer(producer);

                model = modelDao.saveOrUpdate(model);
            } else if (model != null && producer == null) {
                throw new DeliveryServiceException();
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

            if (product.getCurrentPricing() != null && editingProduct.getCurrentPrice() != null) {

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

    public DeliverySearchDto toDeliverySearchDto(Delivery entity) {
        DeliverySearchDto dto = new DeliverySearchDto();

        dto.setId(entity.getId());
        dto.setContactId(entity.getContact().getId());
        dto.setContactLabel(entity.getContact().getLabel());
        dto.setNumberOfProducts(new Long(entity.getProducts().size()));
        dto.setLabel(entity.getLabel());
        dto.setStoreId(entity.getStore().getId());
        dto.setStoreLabel(entity.getStore().getLabel());
        dto.setDateIn(entity.getDateIn());

        double sum = 0;

        for (Product product : entity.getProducts() ) {
            sum += product.getPriceIn();       }

        dto.setSum(sum);

        return dto;
    }
}
