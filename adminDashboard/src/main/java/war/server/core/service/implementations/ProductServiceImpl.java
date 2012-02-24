package war.server.core.service.implementations;


import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.ProductsDao;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.entity.common.ProductStatus;
import war.server.core.service.interfaces.ProductService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

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
        List<Product> products = productsDao.findAll();

        for (Product p : products) {
            if (!res.contains(p.getProducer()))
                res.add(p.getProducer());
        }
        
        logger.debug("found {} elements " , res.size());
        
        return res;
    }

    public List<String> fetchAllModels() {
        logger.debug("ProductServiceImpl.fetchAllModels starts");

        List<String> res = new ArrayList<String>();
        List<Product> products = productsDao.findAll();

        for (Product p : products) {
            if (!res.contains(p.getModel()))
                res.add(p.getModel());
        }

        logger.debug("found {} elements " , res.size());

        return res;
    }

    public List<String> fetchAllColors() {
        logger.debug("ProductServiceImpl.fetchAllModels starts");

        List<String> res = new ArrayList<String>();
        List<Product> products = productsDao.findAll();

        for (Product p : products) {
            if (!res.contains(p.getColor()))
                res.add(p.getColor());
        }

        logger.debug("found {} elements " , res.size());

        return res;
    }


    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus) {

        logger.debug("ProductServiceImpl.fetchAllProducts starts ");
        logger.debug("params : [ storeId : {} , productStatus : {} ] ", storeId, productStatus);

        List<Product> result = productsDao.findByStoreId(storeId, productStatus);

        logger.debug("ProductServiceImpl.fetchAllProducts ends");

        return  result;
    }

    public void moveProducts(Store store, List<Product> products, User user) {        
        logger.debug("ProductServiceImpl.moveProducts starts ");
        logger.debug("params : [ storeId : {} , number of products: {} , userId : {}] ", new Object[] {store, products, user});
        
        em.getTransaction().begin();
        
        for (Product p : products) {
            p.setStore(store);
            p.setEditedBy(user.getId());
            p.setEditedAt(new Date());

            productsDao.save(p);
        }
        
        em.getTransaction().commit();
        
        logger.debug("ProductServiceImpl.fetchAllProducts ends");        
    }
    

    public void updateProducts(List<Product> products, User updatingUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
