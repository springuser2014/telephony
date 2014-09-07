package telephony.core.dao;


import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.SaleFilterCriteria;

/**
 * asd.
 */
public interface SalesDao extends GenericDao<Sale> {

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    List<Sale> findByStore(Store store);

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfSales(Store store);

    /**
     * asd.
     * @param ids asd.
     * @return asd.
     */
    List<Product> findProductsBySalesIds(ArrayList<Long> ids);

    /**
     * asd.
     * @param label asd.
     * @return asd.
     */
	Sale findByLabel(String label);

	List<Sale> find(SaleFilterCriteria filters);
}
