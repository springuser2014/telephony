package war.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.ProductRPCService;
import war.client.service.RPCServiceStatus;
import war.server.core.entity.Product;
import war.server.core.entity.common.ProductStatus;
import war.server.core.service.interfaces.ProductService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;

import java.util.List;


@SuppressWarnings("serial")
@Singleton
public class ProductRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements ProductRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private ProductService productService;

    @Inject
    public ProductRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }


    @Override
    public List<String> fetchAllImeiInUse() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Product> fetchAllProducts(Long storeId, ProductStatus productStatus) {
        logger.debug("ProductRPCServiceImpl.fetchAllProducts starts");
        logger.debug("params : [ storeId : {} , productStatus : {} ] ", storeId, productStatus);

        List<Product> result = productService.fetchAllProducts(storeId, productStatus);

        logger.debug("ProductRPCServiceImpl.fetchAllProducts ends");

        return result;
    }

    @Override
    public RPCServiceStatus moveProducts(Long storeId, List<Product> products, Long userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
