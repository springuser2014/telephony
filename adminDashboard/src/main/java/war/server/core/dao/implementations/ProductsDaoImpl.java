package war.server.core.dao.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.ProductsDao;
import war.server.core.entity.Product;
import war.server.core.entity.Delivery ;
import war.server.core.entity.common.ProductStatus;

import java.util.ArrayList;
import java.util.List;


public class ProductsDaoImpl extends GenericDaoImpl<Product> implements ProductsDao {
    
    private Logger logger =  LoggerFactory.getLogger(getClass());

    public ProductsDaoImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus) {
        logger.debug("findCreatedByUserId starts");
        logger.debug("params : [ userId : {} , productStatus : {} ]", userId, productStatus);

        List<Product> res = em.createQuery("select p from Product p where p.createdBy = ?1 and p.status = ?2 ")
                              .setParameter(1, userId)
                              .setParameter(2, productStatus)
                              .getResultList();

        logger.debug("findCreatedByUserId returns {} elements", res.size());

        return res;
    }

    @Override
    public List<Product> findByStoreId(Long storeId, ProductStatus productStatus) {
        logger.debug("findByStoreId starts");
        logger.debug("params : [ storeId : {} , productStatus : {} ]", storeId, productStatus);

        List<Product> res = null;

        if (productStatus.toString().equals(ProductStatus.IN_STORE.toString())) {

            res = em.createQuery("select p from Product p " +
                                   "left join p.delivery d " +
                                   "left join p.store s " +
                                   "where s.id = ?1 and p.sale_id is null")
                    .setParameter(1, storeId)
                    .getResultList();
        }
        else if (productStatus.toString().equals(ProductStatus.SOLD.toString())) {
            res = em.createQuery("select p from Product p " +
                                 "left join p.delivery d " +
                                 "left join p.store s " +
                                 "where s.id = ?1 and p.sale_id is not null")
                    .setParameter(1, storeId)
                    .getResultList();
        }

        logger.debug("findByStoreId returns {} elements", res.size());

        return res;
    }

    @Override
    public Product findByImei(String imei) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> fetchImeisList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> fetchProducersList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> fetchModelsList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
