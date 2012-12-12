package telephony.core.service.impl;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.interfaces.ProductsDao;
import telephony.core.dao.interfaces.SalesDao;
import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.core.service.interfaces.SaleService;
import telephony.shared.ListOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleServiceImpl extends AbstractBasicService implements SaleService {

    @Inject
    private SalesDao salesDao;

    @Inject
    private ProductsDao productsDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Sale> findAllSales() {
        logger.debug("SaleServiceImpl.findAllSales starts");

        List<Sale> res = salesDao.findUndeleted();

        logger.debug("SaleServiceImpl.findAllSales starts");

        return res;
    }

    public void addNewSale(Sale sale, List<Product> products, User user, Store store) {

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

    public List<Product> fetchAllSalesFrom(Store store, int page, ListOrder order) {
        logger.debug("SaleServiceImpl.fetchAllSalesFrom starts");

        int numberOfElements = 6;

        List<Sale> sales = salesDao.findLastest(store, numberOfElements * page, numberOfElements, order);

        ArrayList<Long> ids = new ArrayList<Long>();

        for (Sale s : sales) {
            ids.add(s.getId());
        }

        List<Product> result = salesDao.findProductsBySalesIds(ids);

        logger.debug("SaleServiceImpl.fetchAllSalesFrom ends");

        return result;
    }


}
