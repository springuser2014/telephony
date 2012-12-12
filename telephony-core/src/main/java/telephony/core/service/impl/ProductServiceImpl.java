package telephony.core.service.impl;


import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.ProductsDao;
import telephony.core.entity.Product;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.entity.common.Money;
import telephony.core.entity.common.ProductStatus;
import telephony.core.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl extends AbstractBasicService implements ProductService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ProductsDao productsDao;

    public List<String> fetchAllImeiInUse() {
        logger.debug("ProductServiceImpl.fetchAllImeiInUse starts");

        List<String> res = productsDao.fetchImeisList();

        logger.debug("found {} elements", res.size());

        return res;
    }

    public List<String> fetchAllProducers() {
        logger.debug("ProductServiceImpl.fetchAllProducers starts");

        List<String> res = new ArrayList<String>();
        List<Product> products = productsDao.findUndeleted();

        for (Product p : products) {
            if (!res.contains(p.getProducer()))
                res.add(p.getProducer());
        }

        logger.debug("found {} elements ", res.size());

        return res;
    }

    public List<String> fetchAllModels() {
        logger.debug("ProductServiceImpl.fetchAllModels starts");

        List<String> res = new ArrayList<String>();
        List<Product> products = productsDao.findUndeleted();

        for (Product p : products) {
            if (!res.contains(p.getModel()))
                res.add(p.getModel());
        }

        logger.debug("found {} elements ", res.size());

        return res;
    }

    public List<String> fetchAllColors() {
        logger.debug("ProductServiceImpl.fetchAllModels starts");

        List<String> res = new ArrayList<String>();
        List<Product> products = productsDao.findUndeleted();

        for (Product p : products) {
            if (!res.contains(p.getColor()))
                res.add(p.getColor());
        }

        logger.debug("found {} elements ", res.size());

        return res;
    }


    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus) {

        logger.debug("ProductServiceImpl.fetchAllProducts starts ");
        logger.debug("params : [ storeId : {} , productStatus : {} ] ", storeId, productStatus);

        List<Product> result = productsDao.findByStoreId(storeId, productStatus);

        logger.debug("ProductServiceImpl.fetchAllProducts ends");

        return result;
    }

    public void moveProducts(Store store, List<Product> products, User user) {
        logger.debug("ProductServiceImpl.moveProducts starts ");
        logger.debug("params : [ storeId : {} , number of products: {} , userId : {}] ", new Object[]{store, products, user});

        getEntityManager().getTransaction().begin();

        for (Product p : products) {
            p.setStore(store);
            productsDao.save(p);
        }

        getEntityManager().getTransaction().commit();

        logger.debug("ProductServiceImpl.fetchAllProducts ends");
    }

    public Product fetchProductByImeiAndStoreId(String imei, Long storeId) {
        logger.debug("ProductServiceImpl.fetchProductByImeiAndStoreId starts");

        Product p = productsDao.findByImeiAndStoreId(imei, storeId);

        return p;
    }

    @Transactional
    public List<Product> fetchAllProductsByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus status) {
        logger.debug("ProductServiceImpl.fetchAllProductsByCriteria starts ");
        Object[] params = new Object[]{imei, producer, model, color, storeId, deliveryDateStart, deliveryDateEnd, status};
        logger.debug("params : [ imei : {} , producer : {} , model : {} , color : {} , storeId : {} , deliveryDateStart : {} , deliveryDateEnd : {}, productStatus : {} ] ", params);

        List<Product> result = productsDao.findByCriteria(imei, producer, model, color, storeId, deliveryDateStart, deliveryDateEnd, status);

        logger.info("ProductServiceImpl.fetchAllProductsByCriteria ends");

        for (Product p : result) {
            logger.info(" model : {} , producer : {} ", p.getModel(), p.getProducer());
        }

        return result;
    }

    public void updateProducts(List<Product> productsToUpdate, List<Product> productsToDelete, List<Product> productsToCancelTheSale, User editor) {
        logger.debug("ProductServiceImpl.updateProducts starts");

        for (Product p : productsToUpdate) {
            for (Product p1 : productsToDelete) {
                if (p1.getId().equals(p.getId())) {
                    productsToUpdate.remove(p);
                }
            }

            for (Product p2 : productsToCancelTheSale) {
                if (p2.getId().equals(p.getId())) {
                    productsToUpdate.remove(p);
                }
            }

        }

        if (productsToUpdate.size() > 0) {
            productsDao.saveOrUpdate(productsToUpdate);
        }

        if (productsToDelete.size() > 0) {
            productsDao.markAsDeleted(productsToDelete, editor);
        }

        if (productsToCancelTheSale.size() > 0) {
            this.cancelProductsSale(productsToCancelTheSale, editor);
        }
    }

    public void cancelProductsSale(List<Product> productsToCancelTheSale, User editor) {
        logger.debug("ProductServiceImpl.cancelProductsSale starts");

        for (Product p : productsToCancelTheSale) {
            p.setSale(null);
            p.setPriceOut(new Money(0, 0));
        }

        productsDao.saveOrUpdate(productsToCancelTheSale);
    }


    public void updateProducts(List<Product> products, User updatingUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
