package telephony.server.core.dao.interfaces;


import telephony.server.core.entity.Product;
import telephony.server.core.entity.common.ProductStatus;

import java.util.Date;
import java.util.List;

public interface ProductsDao extends GenericDao<Product> {
    
    public List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus);

    public List<Product> findByStoreId(Long storeId, ProductStatus productStatus);
    
    public List<Product> findByCriteria(String imei, String producer, String model, String color, Long storeId, Date deliveryDateStart, Date deliveryDateEnd, ProductStatus productStatus);

    public Product findByImei(String imei);

    public List<String> fetchImeisList();

    public List<String> fetchProducersList();

    public List<String> fetchModelsList();

    public Product findByImeiAndStoreId(String imei, Long storeId);
}
