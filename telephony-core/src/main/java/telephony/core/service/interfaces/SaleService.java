package telephony.core.service.interfaces;


import telephony.core.entity.Product;
import telephony.core.entity.Sale;
import telephony.core.entity.Store;
import telephony.core.entity.User;
import telephony.shared.ListOrder;

import java.util.List;

public interface SaleService {

    public List<Sale> findAllSales();

    public void addNewSale(Sale sale, List<Product> products, User user, Store store);

    public List<Product> fetchAllSalesFrom(Store store, int page, ListOrder order);
}
