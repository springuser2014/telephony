package telephony.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.client.service.ProductRPCService;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.server.core.entity.common.ProductStatus;
import telephony.server.core.service.interfaces.ProductService;
import telephony.server.gilead.GuicePersistentRemoteServiceServlet;
import telephony.shared.RPCServiceStatus;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
@Singleton
public class ProductRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements ProductRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ProductService productService;

    @Inject
    private EntityManager em;

    @Inject
    public ProductRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public List<String> fetchAllImeiInUse() {
        logger.debug("ProductRPCServiceImpl.fetchAllImeiInUse starts");

        List<String> imeis = productService.fetchAllImeiInUse();

        return imeis;
    }

    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus) {
        logger.debug("ProductRPCServiceImpl.fetchAllProducts starts");
        logger.debug("params : [ storeId : {} , productStatus : {} ] ", storeId, productStatus);

        List<Product> result = productService.fetchAllProducts(storeId, productStatus);

        return result;
    }

    @Transactional
    public List<Product> fetchAllProductsByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus status) {
        logger.debug("ProductRPCServiceImpl.fetchAllProductsByCriteria starts");

        Object[] params = new Object[]{imei, producer, model, color, storeId, deliveryDateStart, deliveryDateEnd, status};

        logger.debug("params : [ imei : {} , producer : {} , model : {} , color : {} , storeId : {} , deliveryDateStart : {} , deliveryDateEnd : {}, productStatus : {} ] ", params);

        List<Product> result = productService.fetchAllProductsByCriteria(imei, producer, model, color, storeId, deliveryDateStart, deliveryDateEnd, status);

        return result;
    }

    public Product fetchProductByImeiAndStoreId(String imei, Long storeId) {
        logger.debug("ProductRPCServiceImpl.fetchProductByImeiAndStoreId starts");
        logger.debug("params : [ imei: {} , storeId : {} ] ", imei, storeId);

        return productService.fetchProductByImeiAndStoreId(imei, storeId);
    }

    public RPCServiceStatus moveProducts(Store store, List<Product> products, User user) {
        logger.debug("ProductRPCServiceImpl.moveProducts starts");

        RPCServiceStatus result = new RPCServiceStatus();

        try {
            productService.moveProducts(store, products, user);
            result.setStatus(RPCServiceStatus.Status.SUCCESS);
            result.setOperationStatusInfo("Zmiany zostały wprowadzone");
        } catch (Exception e) {
            logger.error("error message : {} , stackTrack : {}", e.getMessage(), e.getStackTrace());
            result.setStatus(RPCServiceStatus.Status.FAILED);
            result.setOperationStatusInfo("Wystąpił błąd podczas wykonywania operacji");
        }

        return result;
    }

    public List<String> fetchAllProducers() {
        logger.debug("ProductRPCServiceImpl.fetchAllProducers starts");

        List<String> producers = productService.fetchAllProducers();

        return producers;
    }

    public List<String> fetchAllModels() {
        logger.debug("ProductRPCServiceImpl.fetchAllModels starts");

        List<String> models = productService.fetchAllModels();

        return models;
    }

    public List<String> fetchAllColors() {
        logger.debug("ProductRPCServiceImpl.fetchAllColors starts");

        List<String> colors = productService.fetchAllColors();

        return colors;
    }

    public RPCServiceStatus updateProducts(List<Product> productsToUpdate, List<Product> productsToDelete, List<Product> productsToCancelTheSale, User editor) {
        logger.debug("ProductRPCServiceImpl.updateProducts starts");

        RPCServiceStatus result = new RPCServiceStatus();

        try {
            em.getTransaction().begin();
            productService.updateProducts(productsToUpdate, productsToDelete, productsToCancelTheSale, editor);
            result.setStatus(RPCServiceStatus.Status.SUCCESS);
            result.setOperationStatusInfo("Zmiany zostały wprowadzone");
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("error message : {} , stackTrack : {}", e.getMessage(), e.getStackTrace());
            result.setStatus(RPCServiceStatus.Status.FAILED);
            result.setOperationStatusInfo("Wystąpił błąd podczas wykonywania operacji");
        }

        return result;

    }
}
