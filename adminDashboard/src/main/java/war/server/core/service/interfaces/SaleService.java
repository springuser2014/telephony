package war.server.core.service.interfaces;


import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.User;

import java.util.List;

public interface SaleService {

    List<Sale> findAllSales();
    
    void addNewSale(Sale sale, List<Product> products, User user);
}
