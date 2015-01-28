package telephony.core.dao;


import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.*;
import telephony.core.query.filter.ProductFilterCriteria;

public interface ProductsDao extends GenericDao<Product> {

    List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus);

    List<Product> findByStore(Store store);

    List<Product> findByCriteria(
        ProductFilterCriteria parameterObject);

    Product findByImei(String imei);

    List<String> fetchImeisList();

    List<String> fetchProducersList();

    List<String> fetchModelsList();

    Product findByImeiAndStoreId(String imei, Long storeId);

	List<Product> findBySale(Sale sale);

	List<Product> findByStoreAndStatus(Long storeId, ProductStatus productStatus);

	List<Product> findByIMEIs(List<String> imeis);

	Product findByIMEI(String imei);

	void removeByDeliveryId(Delivery delvieryToDelete);

    boolean checkIfProductsAreAvailable(Collection<Long> productsIds);

    boolean checkIfProductIsAvailable(Long productId);

    boolean checkIfProductIsAssignedToSale(Long productId, Long saleId);
}
