package war.server.core.dao.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.common.ProductStatus;

import java.util.List;

public interface ProductsDao extends GenericDao<Product> {

    List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus);

    List<Product> findByStoreId(Long storeId, ProductStatus productStatus);

    Product findByImei(String imei);

    List<String> fetchImeisList();

    List<String> fetchProducersList();

    List<String> fetchModelsList();
}
