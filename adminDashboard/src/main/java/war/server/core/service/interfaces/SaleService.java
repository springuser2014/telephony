package war.server.core.service.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.shared.ListOrder;

import java.util.List;

public interface SaleService {

    public List<Sale> findAllSales();
    
    public void addNewSale(Sale sale, List<Product> products, User user, Store store);

    public List<Product> fetchAllSalesFrom(Store store, int page, ListOrder order);
}
