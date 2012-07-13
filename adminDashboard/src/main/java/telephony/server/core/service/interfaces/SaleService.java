package telephony.server.core.service.interfaces;


import telephony.server.core.entity.Product;
import telephony.server.core.entity.Sale;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.shared.ListOrder;

import java.util.List;

public interface SaleService {

    public List<Sale> findAllSales();
    
    public void addNewSale(Sale sale, List<Product> products, User user, Store store);

    public List<Product> fetchAllSalesFrom(Store store, int page, ListOrder order);
}
