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
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

    @Inject
    private ProductsDao productsDao;

    @Override
    public List<String> fetchAllImeiInUse() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> fetchAllProducers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> fetchAllModels() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void updateProducts(List<Product> products, User updatingUser) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus) {

        logger.debug("ProductServiceImpl.fetchAllProducts starts ");
        logger.debug("params : [ storeId : {} , productStatus : {} ] ", storeId, productStatus);

        List<Product> result = productsDao.findByStoreId(storeId, productStatus);

        logger.debug("ProductServiceImpl.fetchAllProducts ends");

        return  result;
    }

    @Override
    public void moveProducts(List<Product> products, Store store) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
