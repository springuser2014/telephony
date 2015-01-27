package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.DeliveryFilterCriteria;

public interface DeliveriesDao extends GenericDao<Delivery> {

    List<Product> findProductsByDeliveriesIds(List<Long> ids);

    long getNumberOfDeliveries(Store store);

	List<Delivery> findByStore(Store stores);

	Delivery findDetailsById(Long deliveryId);

	List<Delivery> find(DeliveryFilterCriteria filters);

    Long count(DeliveryFilterCriteria filters);
}
