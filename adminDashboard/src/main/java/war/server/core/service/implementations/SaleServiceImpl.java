package war.server.core.service.implementations;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.server.core.dao.interfaces.ProductsDao;
import war.server.core.dao.interfaces.SalesDao;
import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.User;
import war.server.core.service.interfaces.SaleService;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class SaleServiceImpl implements SaleService {

    @Inject
    private SalesDao salesDao;
    
    @Inject
    private ProductsDao productsDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private EntityManager em;

    public List<Sale> findAllSales() {
        logger.debug("SaleServiceImpl.findAllSales starts");
        
        List<Sale> res = salesDao.findUndeleted();
        
        logger.debug("SaleServiceImpl.findAllSales starts");

        return res;
    }

    public void addNewSale(Sale sale, List<Product> products, User user) {

        logger.debug("SaleServiceImpl.addNewSale starts");

        em.getTransaction().begin();

        sale.setCreatedAt(new Date());
        sale.setCreatedBy(user.getId());
        sale = salesDao.save(sale);
        
        for (Product p : products) {
            p.setEditedAt(new Date());
            p.setEditedBy(user.getId());
            p.setSale(sale);
            productsDao.save(p);
        }

        em.getTransaction().commit();
    }


}
