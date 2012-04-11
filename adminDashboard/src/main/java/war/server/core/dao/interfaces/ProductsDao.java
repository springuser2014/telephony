package war.server.core.dao.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.common.ProductStatus;

import java.util.Date;
import java.util.List;

public interface ProductsDao extends GenericDao<Product> {
    


    List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus);

    List<Product> findByStoreId(Long storeId, ProductStatus productStatus);
    
    List<Product> findByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus productStatus);

    Product findByImei(String imei);

    List<String> fetchImeisList();

    List<String> fetchProducersList();

    List<String> fetchModelsList();

    Product findByImeiAndStoreId(String imei, Long storeId);
}
