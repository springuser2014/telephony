package telephony.core.dao;


import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.SaleFilterCriteria;

public interface SalesDao extends GenericDao<Sale> {

    List<Sale> findByStore(Store store);

    long getNumberOfSales(Store store);

    List<Product> findProductsBySalesIds(ArrayList<Long> ids);

	Sale findByLabel(String label);

	List<Sale> findByCriteria(SaleFilterCriteria filters);

    Long countByCriteria(SaleFilterCriteria filters);
}
