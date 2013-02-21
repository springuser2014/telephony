package telephony.core.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.interfaces.ProductsDao;
import telephony.core.dao.interfaces.SalesDao;
import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.service.interfaces.SaleService;

import com.google.inject.Inject;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SaleServiceImpl
extends AbstractBasicService implements SaleService {

    /**
     * asd.
     */
    @Inject
    private SalesDao salesDao;

    /**
     * asd.
     */
    @Inject
    private ProductsDao productsDao;

    /**
     * asd.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final List<Sale> findAllSales() {
        logger.debug("SaleServiceImpl.findAllSales starts");

        List<Sale> res = salesDao.findNotRemoved();

        logger.debug("SaleServiceImpl.findAllSales starts");

        return res;
    }

    // TODO: refactor user parameter
    /**
     * asd.
     * @param sale asd.
     * @param products asd.
     * @param user asd.
     * @param store asd.
     */
    @Override
    public final void addNewSale(Sale sale, final List<Product> products,
        final User user, final Store store) {

        logger.debug("SaleServiceImpl.addNewSale starts");

        getEntityManager().getTransaction().begin();

        sale.setCreatedAt(new Date());
        sale.setCreator(user);
        sale.setStore(store);
        sale = salesDao.save(sale);

        for (Product p : products) {
            p.setSale(sale);
            productsDao.save(p);
        }

        getEntityManager().getTransaction().commit();
    }

   /**
    * asd.
    * @param store asd.
    * @param page asd.
    * @return asd.
    */
    public final List<Product> fetchAllSalesFrom(
        final Store store, final int page) {
//        logger.debug("SaleServiceImpl.fetchAllSalesFrom starts");
//
//        int numberOfElements = 6;
//
//        List<Sale> sales = salesDao.findLastest(
//        store, numberOfElements * page, numberOfElements, order);
//
//        ArrayList<Long> ids = new ArrayList<Long>();
//
//        for (Sale s : sales) {
//            ids.add(s.getId());
//        }
//
//        List<Product> result = salesDao.findProductsBySalesIds(ids);
//
//        logger.debug("SaleServiceImpl.fetchAllSalesFrom ends");
//
//        return result;
        return null;
    }


}
